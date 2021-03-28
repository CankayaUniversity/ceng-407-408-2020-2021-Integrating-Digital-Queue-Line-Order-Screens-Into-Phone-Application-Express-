const Menu = require('../models/Menu');
const ErrorResponse = require('../utils/errorResponse');
const asyncHandler = require('../middleware/async');



exports.getMenus = asyncHandler(async (req, res, next) => {

    const menus = await Menu.find();

    res.status(200).json({
        success: true,
        data: menus
    });
});




exports.getMenu = asyncHandler(async (req, res, next) => {

    const menu = await Menu.findById(req.params.id);

    if (!menu) {
        return next(new ErrorResponse(`Menu not found with id of ${req.params.id}`, 404));
    }

    res.status(200).json({
        success: true,
        data: menu
    });
});



exports.createMenu = asyncHandler(async (req, res, next) => {

    const menu = await Menu.create(req.body);

    res.status(200).json({
        success: true,
        data: menu
    })

});


exports.updateMenu = asyncHandler(async (req, res, next) => {

    const menu = await Menu.findOneAndUpdate(req.params.id, req.body, {
        new: true,
        runValidators: true
    });

    if (!menu) {
        return next(new ErrorResponse(`Menu not found with id of ${req.params.id}`, 404));
    }


    res.status(200).json({
        success: true,
        data: menu
    });
});


exports.deleteMenu = asyncHandler(async (req, res, next) => {

    const menu = await Menu.findByIdAndDelete(req.params.id);

    if (!menu) {
        return next(new ErrorResponse(`Restaurant not found with id of ${req.params.id}`, 404));
    }

    res.status(200).json({
        success: true,
        data: menu
    });


});