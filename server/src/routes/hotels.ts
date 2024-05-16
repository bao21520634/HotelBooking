import express, { Request, Response, response } from 'express';
import verifyToken from '../middleware/auth';
import Hotel from '../models/hotel';
import { BookingType, HotelSearchResponse } from '../shared/types';
import { param, validationResult } from 'express-validator';
import Stripe from 'stripe';
import User from '../models/user';
import axios from 'axios';

const stripe = new Stripe(process.env.STRIPE_API_KEY as string);
const router = express.Router();

router.get('/search', verifyToken, async (req: Request, res: Response) => {
    try {
        var { destination, checkIn, checkOut, ...query } = req.query;
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

        const pageSize = 5;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        var hotels = [];
        var user = null;

        if (destination) {
            const locationSearchParams = new URLSearchParams({
                address: destination,
                api_key: process.env.GOONG_API_KEY,
            } as any);

            const location = await axios({
                method: 'GET',
                url: `https://rsapi.goong.io/Geocode?${locationSearchParams.toString()}`,
            });

            console.log(location.data.results);

            const isHotelsExist = await Hotel.exists({ ...query });

            let aggregateQuery: any = [
                {
                    $geoNear: {
                        near: {
                            type: 'Point',
                            coordinates: [
                                location.data.results[0].geometry.location.lng,
                                location.data.results[0].geometry.location.lat,
                            ],
                        },
                        distanceField: 'dist.calculated',
                        maxDistance: 60000,
                        query: isHotelsExist ? query : {},
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
                                    city: location.data.results[0].compound.province,
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
                Hotel.find(query).skip(skip).limit(pageSize),
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

        const total = hotels.length;

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

router.get('/auto-complete', async (req: Request, res: Response) => {
    try {
        const locationSearchParams = new URLSearchParams({
            address: req.query.destination,
            api_key: process.env.GOONG_API_KEY,
        } as any);

        const location = await axios({
            method: 'GET',
            url: `https://rsapi.goong.io/Geocode?${locationSearchParams.toString()}`,
        });

        res.json({ data: location });
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/continue-search', verifyToken, async (req: Request, res: Response) => {
    try {
        const user = await User.findById(req.userId).select('search');

        res.json({ data: user });
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/city/:latlng', async (req: Request, res: Response) => {
    try {
        const locationSearchParams = new URLSearchParams({
            latlng: req.params.latlng,
            api_key: process.env.GOONG_API_KEY,
        } as any);

        const location = await axios({
            method: 'GET',
            url: `https://rsapi.goong.io/Geocode?${locationSearchParams.toString()}`,
        });

        var hotels = await Hotel.find({ city: location.data.results[0].compound.province }).limit(10);

        res.json({
            data: hotels,
        });
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/near-here', async (req: Request, res: Response) => {
    try {
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
        ]).limit(10);

        res.json({
            data: hotels,
        });
    } catch (error) {
        console.log('error', error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

router.get('/owner/:id', async (req: Request, res: Response) => {
    var hotels = await Hotel.find({ userId: req.params.id }).limit(10);

    res.json({
        data: hotels,
    });
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
            const [hotel, user] = await Promise.all([
                Hotel.findById(id),
                User.findByIdAndUpdate(req.userId, { $addToSet: { history: id } }),
            ]);

            res.json({ hotel });
        } catch (error) {
            console.log(error);
            res.status(500).json({ message: 'Error fetching hotel' });
        }
    },
);

router.post('/:hotelId/bookings/payment-intent', verifyToken, async (req: Request, res: Response) => {
    const { numberOfWeekdayNights, numberOfWeekendNights } = req.body;
    const hotelId = req.params.hotelId;

    const hotel = await Hotel.findById(hotelId);
    if (!hotel) {
        return res.status(400).json({ message: 'Hotel not found' });
    }

    const totalCost =
        hotel.pricePerNightWeekdays * numberOfWeekdayNights + hotel.pricePerNightWeekends * numberOfWeekendNights;
    const paymentIntent = await stripe.paymentIntents.create({
        amount: totalCost,
        currency: 'vnd',
        metadata: {
            hotelId,
            userId: req.userId,
        },
    });

    if (!paymentIntent.client_secret) {
        return res.status(500).json({ message: 'Error creating payment intent' });
    }

    const response = {
        paymentIntentId: paymentIntent.id,
        clientSecret: paymentIntent.client_secret.toString(),
        totalCost,
    };

    res.send(response);
});

router.post('/:hotelId/bookings', verifyToken, async (req: Request, res: Response) => {
    try {
        const paymentIntentId = req.body.paymentIntentId;
        const paymentIntent = await stripe.paymentIntents.retrieve(paymentIntentId as string);
        if (!paymentIntent) {
            return res.status(400).json({ message: 'Payment intent not found' });
        }

        if (paymentIntent.status !== 'succeeded') {
            return res.status(400).json({
                message: `payment intent not succeeded. Status: ${paymentIntent.status}`,
            });
        }

        const newBooking: BookingType = {
            ...req.body,
            userId: req.userId,
        };
        const hotel = await Hotel.findOneAndUpdate(
            { _id: req.params.hotelId },
            {
                $push: { bookings: newBooking },
            },
        );

        if (!hotel) {
            return res.status(400).json({ message: 'hotel not found' });
        }

        await hotel.save();
        console.log('success');
        res.status(200).send();
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Something went wrong' });
    }
});

const constructSearchQuery = (queryParams: any) => {
    let constructedQuery: any = {};

    // if (queryParams.destination) {
    //     constructedQuery.$or = [
    //         { city: new RegExp(queryParams.destination, 'i') },
    //         { country: new RegExp(queryParams.destination, 'i') },
    //     ];
    // }

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

export default router;
