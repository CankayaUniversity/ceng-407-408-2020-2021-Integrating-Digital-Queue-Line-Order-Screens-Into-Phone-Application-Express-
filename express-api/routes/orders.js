const express = require('express');
const {
    getOrders,
    getOrder,
    createOrders
} = require('../controllers/orders');


const router = express.Router();

router.route('/').get(getOrders).post(createOrders);

router.route('/:id').get(getOrder);

module.exports = router;