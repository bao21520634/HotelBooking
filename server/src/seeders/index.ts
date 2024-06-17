import userSeeder from './user';
import hotelSeeder from './hotel';
import mongoose from 'mongoose';
import 'dotenv/config';

mongoose
    .connect(process.env.MONGODB_CONNECTION_STRING as string)
    .then(() => console.log('Connected to database: ', process.env.MONGODB_CONNECTION_STRING));

(async () => {
    try {
        await userSeeder();
        await hotelSeeder();
    } catch (err) {
        console.log(err);
    }
})();
