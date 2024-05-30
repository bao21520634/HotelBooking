import express, { Request, Response } from 'express';
import verifyToken from '../middleware/auth';
import Hotel from '../models/hotel';
import User from '../models/user';
import { HotelSearchResponse, HotelType } from '../shared/types';

const router = express.Router();

// /api/my-bookings
router.get('/', verifyToken, async (req: Request, res: Response) => {
    try {
        const pageSize = 20;
        const pageNumber = parseInt(req.query.page ? req.query.page.toString() : '1');
        //pages to skip
        const skip = (pageNumber - 1) * pageSize;

        const [hotels, total] = await Promise.all([
            Hotel.find({
                bookings: { $elemMatch: { userId: req.userId } },
            })
                .skip(skip)
                .limit(pageSize),
            Hotel.countDocuments({ bookings: { $elemMatch: { userId: req.userId } } }),
        ]);

        const results = hotels.map((hotel) => {
            const userBookings = hotel.bookings.filter((booking) => booking.userId === req.userId);

            const hotelWithUserBookings: HotelType = {
                ...hotel.toObject(),
                bookings: userBookings,
            };

            return hotelWithUserBookings;
        });

        const response: HotelSearchResponse = {
            data: results,
            pagination: {
                total,
                page: pageNumber,
                pages: Math.ceil(total / pageSize),
            },
        };

        res.status(200).send(response);
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

export default router;
