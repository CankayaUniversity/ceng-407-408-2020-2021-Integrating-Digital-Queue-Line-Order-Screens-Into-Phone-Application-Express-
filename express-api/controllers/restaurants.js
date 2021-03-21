const Restaurant = require('../models/Restaurant');




/**
 * @swagger
 * components:
 *   schemas:
 *     RestaurantResponse:
 *       type: object
 *       required:
 *         - name
 *         - description
 *         - address
 *       properties:
 *         id:
 *           type: ObjectId
 *           description: The auto-generated id of the restaurant
 *         name:
 *           type: string
 *           description: The restaurant name
 *         slug:
 *           type: string
 *           description: seo compatible url of the restaurant
 *         qrcode:
 *           type: string
 *           description: QR code of the restaurant
 *         description:
 *           type: string
 *           description: Description of the restaurant
 *         website:
 *           type: string
 *           description: website address of the restaurant
 *         phone:
 *           type: string
 *           description: phone number of the restaurant
 *         email:
 *           type: string
 *           description: email address of the restaurant
 *         address:
 *           type: string
 *           description: physical address of the restaurant
 *         averagePoint:
 *           type: number
 *           description: average score given by customers
 *         photo:
 *           type: string
 *           description: photo of the restaurant
 *       example:
 *         id: 60548e510e53a84a875bce88
 *         name: BurgerKing
 *         description: Burger King Corporation, restaurant company specializing in flame-broiled fast-food hamburgers. It is the second largest hamburger chain the the United States, after McDonald's. In the early 21st century, Burger King claimed to have about 14,000 stores in nearly 100 countries. Headquarters are in Miami, Florida.
 *         website: https://www.bk.com/
 *         phone: 123456789
 *         email: bk
 *         address: Dikilitaş Mahallesi Emirhan Caddesi No:109 Beşiktaş / İstanbul
 *     RestaurantRequest:
 *       type: object
 *       required:
 *         - name
 *         - description
 *         - address
 *       properties:
 *         name:
 *           type: string
 *           description: The restaurant name
 *         slug:
 *           type: string
 *           description: seo compatible url of the restaurant
 *         qrcode:
 *           type: string
 *           description: QR code of the restaurant
 *         description:
 *           type: string
 *           description: Description of the restaurant
 *         website:
 *           type: string
 *           description: website address of the restaurant
 *         phone:
 *           type: string
 *           description: phone number of the restaurant
 *         email:
 *           type: string
 *           description: email address of the restaurant
 *         address:
 *           type: string
 *           description: physical address of the restaurant
 *       example:
 *         name: BurgerKing
 *         description: Burger King Corporation, restaurant company specializing in flame-broiled fast-food hamburgers. It is the second largest hamburger chain the the United States, after McDonald's. In the early 21st century, Burger King claimed to have about 14,000 stores in nearly 100 countries. Headquarters are in Miami, Florida.
 *         website: https://www.bk.com/
 *         phone: 123456789
 *         email: bk
 *         address: Dikilitaş Mahallesi Emirhan Caddesi No:109 Beşiktaş / İstanbul
 
 */




/**
 * @swagger
 * tags:
 *   name: Restaurants
 *   description: The restaurants managing API
 */






// @desc            Get all restaurants
// @route           GET /api/v1/restaurants
// @access          Public

/**
 * @swagger
 * /api/v1/restaurants:
 *   get:
 *     summary: Returns the list of all the restaurants
 *     tags: [Restaurants]
 *     responses:
 *       200:
 *         description: The list of the restaurants
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                  success:
 *                      type: boolean
 *                  data:
 *                      type: array
 *                      items:
 *                          $ref: '#/components/schemas/RestaurantResponse'
 * 
 *       500:
 *         description: Some server error 
 */
exports.getRestaurants = async (req, res, next) => {
    try {
        const restaurants = await Restaurant.find();

        res.status(200).json({
            success: true,
            data: restaurants
        })

    } catch (error) {

        res.status(500).json({
            success: false,
            data: error
        })
    }

}


// @desc            Get single restaurant
// @route           GET /api/v1/restaurants/:id
// @access          Public


/**
 * @swagger
 * /api/v1/restaurants/{id}:
 *   get:
 *     summary: Get the restaurant by id
 *     tags: [Restaurants]
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: string
 *         required: true
 *         description: The restaurant id
 *     responses:
 *       200:
 *         description: The restaurant description by id
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                  success:
 *                      type: boolean
 *                  data:
 *                      $ref: '#/components/schemas/RestaurantResponse' 
 *       404:
 *         description: The Restaurant was not found
 * 
 *       500:
 *         description: Some server error
 */
exports.getRestaurant = async (req, res, next) => {

    try {
        const restaurant = await Restaurant.findById(req.params.id);

        if (!restaurant) {
            return res.status(404).json({
                success: false,
                data: `Restaurant not found with id of ${req.params.id}`
            });
        }

        res.status(200).json({
            success: true,
            data: restaurant
        })

    } catch (error) {

        res.status(500).json({
            success: false,
            data: error
        })
    }


}



// @desc            Create new restaurant
// @route           POST /api/v1/restaurants
// @access          Private


/**
 * @swagger
 * /api/v1/restaurants:
 *   post:
 *     summary: Create a new restaurant
 *     tags: [Restaurants]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/RestaurantRequest'
 *     responses:
 *       201:
 *         description: The Restaurants was successfully created
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/RestaurantResponse'
 *       500:
 *         description: Some server error
 */
exports.createRestaurant = async (req, res, next) => {

    try {
        const restaurant = await Restaurant.create(req.body);


        res.status(201).json({
            success: true,
            data: restaurant
        })

    } catch (err) {
        res.status(500).json({
            success: false,
            data: err
        })

    }

}


// @desc            Update restaurant
// @route           PUT /api/v1/restaurants/:id
// @access          Private



/**
 * @swagger
 * /api/v1/restaurants/{id}:
 *  put:
 *    summary: Update the restaurant by the id
 *    tags: [Restaurants]
 *    parameters:
 *      - in: path
 *        name: id
 *        schema:
 *          type: string
 *        required: true
 *        description: The restaurant id
 *    requestBody:
 *      required: true
 *      content:
 *        application/json:
 *          schema:
 *            $ref: '#/components/schemas/RestaurantRequest'
 *    responses:
 *      200:
 *        description: The restaurant was updated
 *        content:
 *          application/json:
 *            schema:
 *              $ref: '#/components/schemas/RestaurantResponse'
 *      404:
 *        description: The restaurant was not found
 *      500:
 *        description: Some error happened
 */
exports.updateRestaurant = async (req, res, next) => {

    try {

        delete req.body.photo;

        restaurant = await Restaurant.findOneAndUpdate(req.params.id, req.body, {
            new: true,
            runValidators: true
        });


        if (!restaurant) {
            res.status(404).json({
                success: false,
                data: `Restaurant not found with id of ${req.params.id}`
            })
        }


        res.status(200).json({
            success: true,
            data: restaurant
        });



    } catch (error) {
        res.status(500).json({
            success: false,
            data: error
        })
    }
}


// @desc            Delete restaurant
// @route           DELETE /api/v1/restaurants/:id
// @access          Private

/**
 * @swagger
 * /api/v1/restaurants/{id}:
 *   delete:
 *     summary: Remove the restaurants by id
 *     tags: [Restaurants]
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: string
 *         required: true
 *         description: The restaurants id
 * 
 *     responses:
 *       200:
 *         description: The restaurants was deleted
 *       404:
 *         description: The restaurants was not found
 *       500:
 *         description: Some error happened
 */
exports.deleteRestaurant = async (req, res, next) => {

    try {
        const restaurant = await Restaurant.findByIdAndDelete(req.params.id);

        if (!restaurant) {
            return res.status(400).json({
                success: false,
                data: `Restaurant not found with id of ${req.params.id}`
            });
        }

        res.status(200).json({
            success: true,
            data: restaurant
        });
    } catch (err) {
        res.status(500).json({
            success: false
        });
    }
}