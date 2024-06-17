import User from '../models/user';
import { faker } from '@faker-js/faker';

// Function to create a random user
const createRandomUser = () => ({
    email: faker.internet.email(),
    password: '$2a$08$3gQ4Qc3/OXRSPa1xHi8LFuYhbfKuQVYcPnpRjpC30RaU8YRA4YG1G',
    username: faker.person.fullName(),
    gender: faker.person.gender(),
    favorites: [], // You can add random ObjectId references to Hotel documents if needed
    history: [], // You can add random ObjectId references to Hotel documents if needed
    search: [],
});

// Generate 100 random users
const generateUsers = (num: number) => {
    const users = [];
    for (let i = 0; i < num; i++) {
        users.push(createRandomUser());
    }
    return users;
};

const seedDB = async () => {
    try {
        // Generate and insert sample data
        var userCount = 99;
        const users = generateUsers(userCount);
        await Promise.all([
            User.insertMany(users),
            User.create({
                email: 'test@example.com',
                password: 'password',
                username: 'test',
                gender: faker.person.gender(),
                favorites: [], // You can add random ObjectId references to Hotel documents if needed
                history: [], // You can add random ObjectId references to Hotel documents if needed
                search: [
                    {
                        city: 'Hồ Chí Minh',
                    },
                ],
            }),
        ]);
        console.log(userCount + 1 + ' users data seeded successfully.');
    } catch (error) {
        console.error('Error seeding database:', error);
    }
};

export default seedDB;
