import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';
import { UserType } from '../shared/types';

const userSchema = new mongoose.Schema(
    {
        avatar: {
            type: String,
            default: 'https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png',
        },
        email: { type: String, required: true, unique: true },
        password: { type: String, required: true },
        username: { type: String, required: true },
        gender: { type: String },
        bio: { type: String },
        favorites: [
            {
                type: mongoose.Types.ObjectId,
                ref: 'Hotel',
            },
        ],
        history: [
            {
                type: mongoose.Types.ObjectId,
                ref: 'Hotel',
            },
        ],
        search: [
            {
                city: { type: String },
                checkIn: { type: Date },
                checkOut: { type: Date },
                adultCount: { type: Number },
                childCount: { type: Number },
            },
        ],
    },
    { versionKey: false },
);

userSchema.pre('save', async function (next) {
    if (this.isModified('password')) {
        this.password = await bcrypt.hash(this.password, 8);
    }
    next();
});

const User = mongoose.model<UserType>('User', userSchema);

export default User;
