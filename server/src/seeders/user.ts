import User from '../models/user';
import { faker } from '@faker-js/faker';

// Function to create a random user
const createRandomUser = () => ({
    email: faker.internet.email(),
    password: '$2a$08$rbjOYSH6WpJ/UWG.KCrDI.PgJYSMNsIf8GZB.LnDYEcZ6Wu.9KPci',
    firstName: faker.person.firstName(),
    lastName: faker.person.lastName(),
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
        var userCount = 100;
        const users = generateUsers(userCount);
        await User.insertMany(users);
        console.log(userCount + ' users data seeded successfully.');
    } catch (error) {
        console.error('Error seeding database:', error);
    }
};

export default seedDB;
