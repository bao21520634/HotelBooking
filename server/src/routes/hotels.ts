import express, { Request, Response, response } from 'express';
import verifyToken from '../middleware/auth';
import Hotel from '../models/hotel';
import { BookingType, HotelSearchResponse } from '../shared/types';
import { param, validationResult } from 'express-validator';
import Stripe from 'stripe';
import User from '../models/user';
import axios from 'axios';
import mongoose from 'mongoose';

const stripe = new Stripe(process.env.STRIPE_SECRET_KEY as string);
const router = express.Router();

router.get('/top-search', async (req: Request, res: Response) => {
    try {
        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        const [hotels, total] = await Promise.all([
            User.aggregate([
                {
                    $group: {
                        _id: '$search.city',
                        count: { $sum: 1 },
                    },
                },
                { $sort: { count: -1 } },
                {
                    $match: {
                        '_id.0': {
                            $exists: true,
                        },
                    },
                },
                {
                    $lookup: {
                        from: 'hotels',
                        localField: '_id',
                        foreignField: 'city',
                        as: 'hotels',
                    },
                },
                {
                    $match: {
                        'hotels.quantity': {
                            $ne: 0,
                        },
                    },
                },
            ])
                .skip(skip)
                .limit(pageSize),
            Hotel.countDocuments({}),
        ]);

        const response: HotelSearchResponse = {
            data: hotels[0]?.hotels || [],
            pagination: {
                total,
                page: pageNumber,
                pages: Math.ceil(total / pageSize),
            },
        };
        res.json(response);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/top-bookings', async (req: Request, res: Response) => {
    try {
        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        const [hotels, total] = await Promise.all([
            Hotel.aggregate([
                {
                    $addFields: {
                        length: { $size: '$bookings' },
                    },
                },
                { $sort: { length: -1 } },
                {
                    $match: {
                        quantity: {
                            $ne: 0,
                        },
                    },
                },
            ])
                .skip(skip)
                .limit(pageSize),
            Hotel.countDocuments({}),
        ]);

        const response: HotelSearchResponse = {
            data: hotels,
            pagination: {
                total,
                page: pageNumber,
                pages: Math.ceil(total / pageSize),
            },
        };

        res.json(response);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/search', verifyToken, async (req: Request, res: Response) => {
    try {
        var { place_id, checkIn, checkOut, ...query } = req.query;
        query = constructSearchQuery(req.query);
        let sortOptions: any = {};
        switch (req.query.sortOption) {
            case 'starRating':
                sortOptions = { starRating: -1 };
                break;
            case 'pricePerNightAsc':
                sortOptions = { pricePerNightWeekdays: 1 };
                break;
            case 'pricePerNightDesc':
                sortOptions = { pricePerNightWeekdays: -1 };
                break;
        }

        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        var hotels = [];
        var user = null;

        if (place_id) {
            const locationSearchParams = new URLSearchParams({
                place_id: place_id,
                api_key: process.env.GOONG_API_KEY,
            } as any);

            const location = await axios({
                method: 'GET',
                url: `https://rsapi.goong.io/Place/Detail?${locationSearchParams.toString()}`,
            });

            console.log(location.data.results);

            const isHotelsExist = await Hotel.exists({ ...query });

            let aggregateQuery: any = [
                {
                    $geoNear: {
                        near: {
                            type: 'Point',
                            coordinates: [
                                location.data.result.geometry.location.lng,
                                location.data.result.geometry.location.lat,
                            ],
                        },
                        distanceField: 'dist.calculated',
                        maxDistance: 60000,
                        query: isHotelsExist ? query : {},
                    },
                },
                {
                    $match: {
                        quantity: {
                            $ne: 0,
                        },
                    },
                },
            ];

            if (Object.keys(sortOptions).length !== 0) {
                aggregateQuery.push({ $sort: sortOptions });
            }

            [hotels, user] = await Promise.all([
                Hotel.aggregate(aggregateQuery).skip(skip).limit(pageSize),
                User.updateOne(
                    {
                        _id: req.userId,
                    },
                    {
                        $addToSet: {
                            search: [
                                {
                                    city: location.data.result.compound.province,
                                    checkIn,
                                    checkOut,
                                    adultCount: req.query.childCount,
                                    childCount: req.query.adultCount,
                                },
                            ],
                        },
                    },
                ),
            ]);
        } else {
            [hotels, user] = await Promise.all([
                Hotel.find(query).where('quantity').ne(0).skip(skip).limit(pageSize).lean(),
                User.updateOne(
                    {
                        _id: req.userId,
                    },
                    {
                        $addToSet: {
                            search: [
                                {
                                    checkIn,
                                    checkOut,
                                    adultCount: req.query.childCount,
                                    childCount: req.query.adultCount,
                                },
                            ],
                        },
                    },
                ),
            ]);
        }

        const { countWeekdays, countWeekends }: any = datesCount(checkIn, checkOut);

        const hotelsWithTotalPrice = hotels.map((hotel) => {
            const totalPrice =
                hotel.pricePerNightWeekdays * countWeekdays + hotel.pricePerNightWeekends * countWeekends;

            return {
                ...hotel,
                totalPrice: totalPrice,
            };
        });

        const total = hotels.length;

        const response: HotelSearchResponse = {
            data: hotelsWithTotalPrice,
            pagination: {
                total,
                page: pageNumber,
                pages: Math.ceil(total / pageSize),
            },
        };

        res.json(response);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/auto-complete', async (req: Request, res: Response) => {
    try {
        const locationSearchParams = new URLSearchParams({
            api_key: process.env.GOONG_API_KEY,
            input: req.query.destination,
        } as any);

        const location = await axios({
            method: 'GET',
            url: `https://rsapi.goong.io/Place/AutoComplete?${locationSearchParams.toString()}`,
        });

        const results = location.data.predictions.map((location: any) => ({
            place_id: location.place_id,
            place_name: location.description,
        }));

        res.json(results);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/continue-search', verifyToken, async (req: Request, res: Response) => {
    try {
        const data = await User.findById(req.userId).select('search');

        res.json(data);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/city/:latlng', async (req: Request, res: Response) => {
    try {
        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        const locationSearchParams = new URLSearchParams({
            latlng: req.params.latlng,
            api_key: process.env.GOONG_API_KEY,
        } as any);

        const location = await axios({
            method: 'GET',
            url: `https://rsapi.goong.io/Geocode?${locationSearchParams.toString()}`,
        });

        var hotels = await Hotel.find({ city: location.data.results[0].compound.province })
            .where('quantity')
            .ne(0)
            .limit(20);

        const response: HotelSearchResponse = {
            data: hotels,
            pagination: {
                total: 20,
                page: pageNumber,
                pages: Math.ceil(20 / pageSize),
            },
        };

        res.json(hotels);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/near-here', async (req: Request, res: Response) => {
    try {
        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        var hotels = await Hotel.aggregate([
            {
                $geoNear: {
                    near: {
                        type: 'Point',
                        coordinates: [parseInt(req.query.lng as string), parseInt(req.query.lat as string)],
                    },
                    distanceField: 'dist.calculated',
                    maxDistance: 60000,
                },
            },
            {
                $match: {
                    quantity: {
                        $ne: 0,
                    },
                },
            },
        ]).limit(20);

        const response: HotelSearchResponse = {
            data: hotels,
            pagination: {
                total: 20,
                page: pageNumber,
                pages: Math.ceil(20 / pageSize),
            },
        };

        res.json(response);
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get(
    '/:id',
    verifyToken,
    [param('id').notEmpty().withMessage('Hotel ID is required')],
    async (req: Request, res: Response) => {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }

        const id = req.params.id.toString();

        try {
            const isExisted = await Hotel.exists({
                quantity: {
                    $ne: 0,
                },
            });

            if (isExisted) {
                const [hotel, user] = await Promise.all([
                    Hotel.findById(id),
                    User.findByIdAndUpdate(req.userId, { $addToSet: { history: new mongoose.Types.ObjectId(id) } }),
                ]);

                res.json(hotel);
            } else {
                res.json({});
            }
        } catch (error) {
            console.log(error);
            res.status(500).json({ message: 'Error fetching hotel' });
        }
    },
);

router.post('/:hotelId/bookings/payment', verifyToken, async (req: Request, res: Response) => {
    const hotelId = req.params.hotelId;

    const hotel = await Hotel.findById(hotelId).where('quantity').ne(0);
    if (!hotel) {
        return res.status(200).json({ message: 'Hotel not available' });
    }

    const totalCost = parseInt(req.body.totalCost as string, 10);
    if (isNaN(totalCost)) {
        return res.status(400).json({ message: 'Invalid total cost value' });
    }

    const session = await stripe.checkout.sessions.create({
        line_items: [
            {
                price_data: {
                    currency: 'vnd',
                    product_data: {
                        name: hotel.name,
                        images: [hotel.imageUrls[0]],
                    },
                    unit_amount: totalCost,
                },
                quantity: 1,
            },
        ],
        mode: 'payment',
        customer_creation: 'always',
        success_url: req.body.appUrl || 'https://www.google.com/',
        cancel_url: 'https://www.google.com/',
    });

    const newBooking: BookingType = {
        ...req.body,
        userId: req.userId,
    };

    hotel.bookings.push(newBooking);
    hotel.quantity -= 1;
    hotel.save();

    res.status(200).send({ url: session.url });
});

const constructSearchQuery = (queryParams: any) => {
    let constructedQuery: any = {};

    if (queryParams.city) {
        constructedQuery.city = queryParams.city;
    }

    if (queryParams.adultCount) {
        constructedQuery.adultCount = {
            $gte: parseInt(queryParams.adultCount),
        };
    }

    if (queryParams.childCount) {
        constructedQuery.childCount = {
            $gte: parseInt(queryParams.childCount),
        };
    }

    if (queryParams.roomCount) {
        constructedQuery.roomCount = {
            [`name.${queryParams.roomCount}`]: { $exists: true },
        };
    }

    if (queryParams.facilities) {
        constructedQuery.facilities = {
            $all: Array.isArray(queryParams.facilities) ? queryParams.facilities : [queryParams.facilities],
        };
    }

    if (queryParams.types) {
        constructedQuery.type = {
            $in: Array.isArray(queryParams.types) ? queryParams.types : [queryParams.types],
        };
    }

    if (queryParams.stars) {
        const starRatings = Array.isArray(queryParams.stars)
            ? queryParams.stars.map((star: string) => parseInt(star))
            : parseInt(queryParams.stars);

        constructedQuery.starRating = { $in: starRatings };
    }

    if (queryParams.maxPrice) {
        constructedQuery.pricePerNightWeekdays = {
            $lte: parseInt(queryParams.maxPrice).toString(),
        };
    }

    return constructedQuery;
};

const datesCount = (startDate: any, endDate: any) => {
    var startDate: any = new Date(startDate);
    var endDate: any = new Date(endDate);

    let countWeekdays = 0;
    let countWeekends = 0;
    const curDate = new Date(startDate.getTime());
    const nextDate = new Date(endDate.getTime());
    while (curDate <= nextDate) {
        const dayOfWeek = curDate.getDay();
        if (dayOfWeek !== 0 && dayOfWeek !== 6) countWeekdays++;
        else countWeekends++;
        curDate.setDate(curDate.getDate() + 1);
    }

    return { countWeekdays, countWeekends };
};

export default router;
