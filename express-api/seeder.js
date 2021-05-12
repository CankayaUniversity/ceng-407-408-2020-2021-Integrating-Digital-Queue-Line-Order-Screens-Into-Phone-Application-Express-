const fs = require('fs');
const mongoose = require('mongoose');
const dotenv = require('dotenv');

// Load env vars
dotenv.config({
  path: './config/config.env',
});

// Load models
const Restaurant = require('./models/Restaurant');
const Menu = require('./models/Menu');
const MenuItem = require('./models/MenuItem');
const Order = require('./models/Order');

mongoose.connect(process.env.MONGO_URI, {
  useNewUrlParser: true,
  useCreateIndex: true,
  useFindAndModify: false,
  useUnifiedTopology: true,
});

// Read JSON files
const restaurant = JSON.parse(
  fs.readFileSync(`${__dirname}/_data/restaurants.json`, 'utf-8')
);

const menu = JSON.parse(
  fs.readFileSync(`${__dirname}/_data/menus.json`, 'utf-8')
);

const menuitem = JSON.parse(
  fs.readFileSync(`${__dirname}/_data/menuItems.json`, 'utf-8')
);

// Import into DB
const importData = async () => {
  try {
    await Restaurant.create(restaurant);
    await Menu.create(menu);
    await MenuItem.create(menuitem);

    console.log('Data Imported...');
    process.exit();
  } catch (err) {
    console.error(err);
  }
};

// Delete data
const deleteData = async () => {
  try {
    await Restaurant.deleteMany();
    await Menu.deleteMany();
    await MenuItem.deleteMany();
    await Order.deleteMany();

    console.log('Data Destroyed...');
    process.exit();
  } catch (err) {
    console.error(err);
  }
};

if (process.argv[2] === '-i') {
  importData();
} else if (process.argv[2] === '-d') {
  deleteData();
}
