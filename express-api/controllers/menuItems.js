const MenuItem = require('../models/MenuItem');
const ErrorResponse = require('../utils/errorResponse');
const asyncHandler = require('../middleware/async');


exports.getMenuItems = asyncHandler(async (req, res, next) => {

    const menuItems = await MenuItem.find();

    res.status(200).json({
        success: true,
        data: menuItems
    });
});


exports.getMenuItem = asyncHandler(async (req, res, next) => {

    const menuItem = await MenuItem.find(req.params.id);

    if (!menuItem) {
        return next(new ErrorResponse(`Menu not found with id of ${req.params.id}`, 404));
    }

    res.status(200).json({
        success: true,
        data: menuItem
    });

});

exports.createMenuItem = asyncHandler(async (req, res, next) => {

    const menuItem = await MenuItem.create(req.body);

    res.status(201).json({
        success: true,
        data: menuItem
    });
});


exports.updateMenuItem = asyncHandler(async (req, res, next) => {

    const menuItem = await MenuItem.findOneAndUpdate(req.params.id, req.body, {
        new: true,
        runValidators: true
    });

    if (!menuItem) {
        return next(new ErrorResponse(`Menu item not found with id of ${req.params.id}`, 404));
    }

    res.status(200).json({
        success: true,
        data: menu
    });

});



exports.deleteMenuItem = asyncHandler(async (req, res, next) => {

    const menuItem = await MenuItem.findById(req.params.id);

    if (!menuItem){
        return next(new ErrorResponse(`Restaurant not found with id of ${req.params.id}`, 404));
    }

    menuItem.remove();

    res.status(200).json({
        success: true,
        data: menuItem
    })
})
