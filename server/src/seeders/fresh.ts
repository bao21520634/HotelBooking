import userSeeder from './user';
import hotelSeeder from './hotel';
import mongoose from 'mongoose';
import 'dotenv/config';

mongoose
    .connect(process.env.MONGODB_CONNECTION_STRING as string)
    .then(() => console.log('Connected to database: ', process.env.MONGODB_CONNECTION_STRING));

(async () => {
    try {
        const collections = mongoose.connection.collections;

        await Promise.all(Object.values(collections).map((collection) => collection.deleteMany({})));

        await userSeeder();
        await hotelSeeder();
    } catch (err) {
        console.log(err);
    }
})();
