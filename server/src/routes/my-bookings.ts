import express, { Request, Response } from 'express';
import verifyToken from '../middleware/auth';
import Hotel from '../models/hotel';
import User from '../models/user';
import { HotelType } from '../shared/types';

const router = express.Router();

// /api/my-bookings
router.get('/', verifyToken, async (req: Request, res: Response) => {
    try {
        const hotels = await Hotel.find({
            bookings: { $elemMatch: { userId: req.userId } },
        });

        const results = hotels.map((hotel) => {
            const userBookings = hotel.bookings.filter((booking) => booking.userId === req.userId);

            const hotelWithUserBookings: HotelType = {
                ...hotel.toObject(),
                bookings: userBookings,
            };

            return hotelWithUserBookings;
        });

        res.status(200).send(results);
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

// [GET] api/my-bookings/favorites
router.get('/favorites', verifyToken, async (req: Request, res: Response) => {
    try {
        const user = await User.findOne({ _id: req.userId }).populate('favorites').exec();

        res.status(200).send({ hotels: user?.favorites });
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

// [POST] api/my-bookings/favorites/:hotelId
router.post('/favorites/:hotelId', verifyToken, async (req: Request, res: Response) => {
    const hotelId = req.params.hotelId.toString();

    try {
        const user = await User.findByIdAndUpdate(
            req.userId,
            [
                {
                    $set: {
                        favorites: {
                            $cond: [
                                {
                                    $in: [hotelId, '$favorites'],
                                },
                                {
                                    $setDifference: ['$favorites', [hotelId]],
                                },
                                {
                                    $concatArrays: ['$favorites', [hotelId]],
                                },
                            ],
                        },
                    },
                },
            ],
            { new: true },
        )
            .populate('favorites')
            .exec();

        res.status(200).send({ user });
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

// [GET] api/my-bookings/history
router.get('/history', verifyToken, async (req: Request, res: Response) => {
    try {
        const user = await User.findOne({ _id: req.userId }).populate('history').exec();

        res.status(200).send({ hotels: user?.history });
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

export default router;
