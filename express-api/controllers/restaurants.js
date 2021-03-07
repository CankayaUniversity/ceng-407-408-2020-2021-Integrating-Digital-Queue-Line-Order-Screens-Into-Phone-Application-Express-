

// @desc            Get all restaurants
// @route           GET /api/v1/restaurants
// @access          Public
exports.getRestaurants = (req, res, next) => {
    res.json({
        message: 'Get all restaurants'
    })

}


// @desc            Get single restaurant
// @route           GET /api/v1/restaurants/:id
// @access          Public
exports.getRestaurant = (req, res, next) => {
    res.json({
        message: 'Get single restaurant'
    })

}



// @desc            Create new restaurant
// @route           POST /api/v1/restaurants
// @access          Private
exports.createRestaurant = (req, res, next) => {
    res.json({
        message: 'Create new restaurant'
    })

}


// @desc            Update restaurant
// @route           PUT /api/v1/restaurants/:id
// @access          Private
exports.updateRestaurant = (req, res, next) => {

    res.json({
        message: 'Update restaurant'
    })
}


// @desc            Delete restaurant
// @route           DELETE /api/v1/restaurants/:id
// @access          Private
exports.deleteRestaurant = (req, res, next) => {

    res.json({
        message: 'Delete restaurant'
    })
}