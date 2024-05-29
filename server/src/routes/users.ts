import express, { Request, Response } from 'express';
import User from '../models/user';
import 'dotenv/config';
import verifyToken from '../middleware/auth';

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

export default router;
