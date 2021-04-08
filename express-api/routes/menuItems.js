const express = require('express');
const {
    getMenuItems,
    getMenuItem,
    createMenuItem,
    updateMenuItem,
    deleteMenuItem
} = require('../controllers/menuItems');


const router = express.Router({
    mergeParams: true
});

router.route('/').get(getMenuItems).post(createMenuItem);

router.route('/:id').get(getMenuItem).put(updateMenuItem).delete(deleteMenuItem);


module.exports = router;