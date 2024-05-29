export type UserType = {
    _id: string;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    favorites: Array<String>;
    history: Array<String>;
    search: Array<Object>;
};

export type HotelType = {
    _id: string;
    userId: string;
    name: string;
    quantity: number;
    city: string;
    country: string;
    address: string;
    location: Object;
    description: string;
    type: string;
    adultCount: number;
    childCount: number;
    facilities: string[];
    bedrooms: Array<Object>;
    interior: Array<Object>;
    pricePerNightWeekdays: number;
    pricePerNightWeekends: number;
    starRating: number;
    imageUrls: string[];
    lastUpdated: Date;
    bookings: BookingType[];
};

export type BookingType = {
    _id: string;
    userId: string;
    username: string;
    email: string;
    adultCount: number;
    childCount: number;
    checkIn: Date;
    checkOut: Date;
    totalCost: number;
};

export type HotelSearchResponse = {
    data: HotelType[];
    pagination: {
        total: number;
        page: number;
        pages: number;
    };
};

export type PaymentIntentResponse = {
    paymentIntentId: string;
    clientSecret: string;
    totalCost: number;
};
