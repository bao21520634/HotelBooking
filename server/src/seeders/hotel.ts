import Hotel from '../models/hotel';
import fs from 'fs';
import path from 'path';
import { faker } from '@faker-js/faker';
import { MongoClient } from 'mongodb';

const client = new MongoClient("mongodb://localhost:27017/user");

const createHotel = (userJson: any, hotelJson: any) => ({
  userId: userJson._id,
  name: hotelJson.title,
  quantity: 5,
  city: hotelJson.city,
  country: "Việt Nam",
  address: hotelJson.address,
  location: hotelJson.latlng,
  description: hotelJson.description,
  guestCount: Number(hotelJson.bedrooms.length *2),
  facilities: hotelJson.facilites,
  bedrooms:[{type:"giường đôi cỡ lớn",quantity: 1}],
  interior: hotelJson.interior,
  pricePerNightWeekdays: Number(hotelJson.price),
  pricePerNightWeekends: Number(hotelJson.price) + 10000000,
  starRating: hotelJson.ratingStars,
  imageUrls: hotelJson.images,
  lastUpdated: faker.date.recent(),
  bookings: [],
});

const generateHotel = async () => {
  // connect to database
  await client.connect();
  console.log('Connect to database successfully!');

  // Path to json directory
  const dirPath = path.resolve('../server/public/data/hotel');
  const jsonInDir = fs.readdirSync(dirPath).filter((file) => path.extname(file) === '.json');

  try {
    // cCollect users collection
    const db = client.db('user');
    const userCollection = db.collection('users');
    const users = await userCollection.find({}).toArray();

    // Read JSON files
    for (const file of jsonInDir) {
      console.log(`File is being read: ${file}`);

      // Read JSON files
      const fileData = fs.readFileSync(path.join(dirPath, file));
      const json = JSON.parse(fileData.toString());
      const filteredJson = json.filter((item :any) => Object.values(item).some(value => value !== ''));
      console.log('JSON file:', filteredJson);

      for (const element of json) {
        // Pick random user
        const randomIndex = Math.floor(Math.random() * users.length);
        const randomUser = users[randomIndex];

        //create hotel and insert into database
        const hotel = createHotel(randomUser, element);
        await db.collection('Hotels').insertOne(hotel);
      }
    }
  } catch (err) {
    console.log('Error when seeding data:', err);
  } finally {
    await client.close();
    console.log('Closed Mongo Connection.');
  }
  console.log('Seeding Hotel Data Succesfully.');
};

generateHotel().catch(console.error);
