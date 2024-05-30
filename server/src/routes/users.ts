import express, { Request, Response } from 'express';
import User from '../models/user';
import 'dotenv/config';
import verifyToken from '../middleware/auth';
import mongoose from 'mongoose';

const router = express.Router();

//verify user
router.get('/me', verifyToken, async (req: Request, res: Response) => {
    const userId = req.userId;

    try {
        const user = await User.findById(userId).select('-password');
        if (!user) {
            return res.status(400).json({ message: 'User not found' });
        }
        res.json(user);
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'something went wrong' });
    }
});

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
                                    $in: [new mongoose.Types.ObjectId(hotelId), '$favorites'],
                                },
                                {
                                    $setDifference: ['$favorites', [new mongoose.Types.ObjectId(hotelId)]],
                                },
                                {
                                    $concatArrays: ['$favorites', [new mongoose.Types.ObjectId(hotelId)]],
                                },
                            ],
                        },
                    },
                },
            ],
            { new: true },
        ).exec();

        res.status(200).send(user);
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: 'Unable to fetch bookings' });
    }
});

export default router;
