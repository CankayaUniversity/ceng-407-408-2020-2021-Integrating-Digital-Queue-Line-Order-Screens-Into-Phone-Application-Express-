const Order = require('../models/Order');
const ErrorResponse = require('../utils/errorResponse');
const asyncHandler = require('../middleware/async');

exports.getOrders = asyncHandler(async (req, res, next) => {
  const orders = await Order.find().populate('menuItem');

  res.status(200).json({
    success: true,
    data: orders,
  });
});

exports.getOrder = asyncHandler(async (req, res, next) => {
  const order = await Order.findById(req.params.id).populate('menuItem');

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
