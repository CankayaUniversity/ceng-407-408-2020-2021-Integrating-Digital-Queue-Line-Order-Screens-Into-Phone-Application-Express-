const express = require('express');
const dotenv = require('dotenv');
const cors = require('cors');
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');
const connectDB = require('./config/db');

// Load env vars
dotenv.config({
    path: './config/config.env'
});

// Connect to database
connectDB();

const options = {
    definition: {
        openapi: "3.0.0",
        info: {
            title: "Express API",
            version: "1.0.0",
            description: "Documentation for front-end and mobile developers who will use the Express API",
        },
        servers: [{
            url: "http://104.248.207.133:5000",
        }, ],
    },
    apis: ["./controllers/*.js"],
};

const specs = swaggerJsDoc(options);

const app = express();

app.use("/api-docs", swaggerUI.serve, swaggerUI.setup(specs));



// Route files
const restaurants = require('./routes/restaurants')



// Body parser
app.use(express.json());


// Enable CORS
app.use(cors());


// Routes
app.use('/api/v1/restaurants', restaurants);




app.use('/api/v1/project-info', (req, res, next) => {
    res.json({
        project_name: "express",
        project_members: ["can", "suhan", "talha"]
    })
});


const server = app.listen(5000, () => {
    console.log(`Server running in 5000`)
});