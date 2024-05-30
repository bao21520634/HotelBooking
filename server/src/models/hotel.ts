import mongoose from 'mongoose';
import { HotelType, BookingType } from '../shared/types';

const bookingSchema = new mongoose.Schema<BookingType>(
    {
        userId: { type: String, required: true },
        adultCount: { type: Number, required: true },
        childCount: { type: Number, required: true },
        checkIn: { type: Date, required: true },
        checkOut: { type: Date, required: true },
        totalCost: { type: Number, required: true },
    },
    {
        timestamps: true,
    },
);

const hotelSchema = new mongoose.Schema<HotelType>(
    {
        userId: { type: String, required: true },
        name: { type: String, required: true },
        quantity: { type: Number, required: true },
        city: { type: String, required: true },
        country: { type: String, required: true },
        address: { type: String, required: true },
        location: {
            type: { type: String, default: 'Point' },
            coordinates: [
                { type: Number, required: true },
                { type: Number, required: true },
            ],
        },
        description: { type: String, required: true },
        type: { type: String, required: true },
        adultCount: { type: Number, required: true },
        childCount: { type: Number, required: true },
        facilities: [{ type: String }],
        bedrooms: [
            {
                type: { type: String, required: true },
                quantity: { type: Number, required: true },
            },
        ],
        interior: [{ type: String }],
        pricePerNightWeekdays: { type: Number, required: true },
        pricePerNightWeekends: { type: Number, required: true },
        starRating: { type: Number, default: 4 },
        imageUrls: [{ type: String, required: true }],
        lastUpdated: { type: Date, required: true },
        bookings: [bookingSchema],
    },
    { versionKey: false },
);

hotelSchema.index({ location: '2dsphere' });
const Hotel = mongoose.model<HotelType>('Hotel', hotelSchema);
export default Hotel;
