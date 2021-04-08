const express = require('express');
const {
    getRestaurants,
    getRestaurant,
    createRestaurant,
    updateRestaurant,
    deleteRestaurant
} = require('../controllers/restaurants');

const router = express.Router();

// Include other resource routers
const menuItemsRouter = require('./menuItems');
const menuRouter = require('./menus');


// Re-route into other resource routers
router.use('/:restaurantId/menuitems', menuItemsRouter);
router.use('/:restaurantId/menus', menuRouter);

router.route('/').get(getRestaurants).post(createRestaurant);

router.route('/:id').get(getRestaurant).put(updateRestaurant).delete(deleteRestaurant);


module.exports = router;