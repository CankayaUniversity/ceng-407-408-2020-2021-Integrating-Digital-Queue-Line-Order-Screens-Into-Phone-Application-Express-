const Order = require('../models/Order');
const ErrorResponse = require('../utils/errorResponse');
const asyncHandler = require('../middleware/async');

exports.getOrders = asyncHandler(async (req, res, next) => {
  let restaurant;
  let user;

  if (req.query.restaurant) {
    restaurant = req.query.restaurant;
  } else if (req.query.user) {
    user = req.query.user;
  }

  let orders;
  if (restaurant) {
    orders = await Order.find({ restaurant: restaurant, isFinished: 0 })
      .populate('menuItem')
      .populate('user');
  } else if (user) {
    orders = await Order.find({ user: user })
      .populate('menuItem')
      .populate('user');
  } else {
    orders = await Order.find().populate('menuItem').populate('user');
  }

  res.status(200).json({
    success: true,
    data: orders,
  });
});

exports.getOrder = asyncHandler(async (req, res, next) => {
  const order = await Order.findById(req.params.id)
    .populate('menuItem')
    .populate('user');

  if (!order) {
    return next(
      new ErrorResponse(`Order not found with id of ${req.params.id}`, 404)
    );
  }

  res.status(200).json({
    success: true,
    data: order,
  });
});

exports.createOrders = asyncHandler(async (req, res, next) => {
  const orders = await Order.create(req.body);

  res.status(200).json({
    success: true,
    data: orders,
  });
});
