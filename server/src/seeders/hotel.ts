import fs from 'fs';
import path from 'path';
import User from '../models/user';
import Hotel from '../models/hotel';

const createHotel = (userJson: any, hotelJson: any) => {
    var bedrooms = hotelJson.bedrooms.filter((bedroom: string) => !!bedroom);
    const latlng = hotelJson.latlng.split(',');
    const lnglat = [latlng[1], latlng[0]];
    const pricePerNightWeekdays = Number(hotelJson.price) || (Math.floor(Math.random() * 299) + 89) * 10000;

    return {
        userId: userJson._id,
        name: hotelJson.title,
        quantity: 5,
        city: hotelJson.city,
        country: 'Việt Nam',
        address: hotelJson.address,
        location: {
            type: 'Point',
            coordinates: lnglat,
        },
        type: 'Khách sạn',
        description: hotelJson.description,
        guestCount: Number(bedrooms.length * 2),
        facilities: hotelJson.facilites,
        bedrooms: bedrooms.map((bedroom: string) => ({ type: bedroom, quantity: 1 })),
        interior: hotelJson.interior,
        pricePerNightWeekdays: pricePerNightWeekdays,
        pricePerNightWeekends: pricePerNightWeekdays + Math.pow(10, pricePerNightWeekdays.toString().length - 1),
        starRating: hotelJson.ratingStars,
        imageUrls: hotelJson.images.filter((image: any) => !image.includes('https://www.booking.com/hotel')),
        lastUpdated: new Date(),
        bookings: [],
    };
};

const generateHotel = async () => {
    // Path to json directory
    const dirPath = path.resolve('../server/public/data/hotel');
    const jsonInDir = fs.readdirSync(dirPath).filter((file) => path.extname(file) === '.json');

    try {
        // cCollect users collection
        const users = await User.find({});
        var hotels = [];

        // Read JSON files
        for (const file of jsonInDir) {
            console.log(`File is being read: ${file}`);

            // Read JSON files
            const fileData = fs.readFileSync(path.join(dirPath, file));
            const json = JSON.parse(fileData.toString());
            const filteredJson = json.filter((item: any) => Object.values(item).some((value) => value !== '')); //filter data has null string

            for (const element of filteredJson) {
                // Pick random user
                const randomIndex = Math.floor(Math.random() * users.length);
                const randomUser = users[randomIndex];

                //create hotel and insert into database
                const hotel = createHotel(randomUser, element);
                hotels.push(hotel);
            }
        }

        const result = await Hotel.insertMany(hotels);

        console.log(result.length, ' hotels data seeded successfully.');
    } catch (err) {
        console.log('Error when seeding data:', err);
    }
};

export default generateHotel;
