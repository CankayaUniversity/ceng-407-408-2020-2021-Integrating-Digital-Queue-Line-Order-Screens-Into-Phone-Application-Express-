const express = require('express');


// Route files
const restaurants = require('./routes/restaurants')


const app = express();

// Body parser
app.use(express.json());



app.use('/api/v1/restaurants', restaurants);


app.use('/api/v1/project-info', (req, res, next) => {
    res.json({
        project_name: "express",
        project_members: ["can", "suhan", "talha"]
    })
});


const server = app.listen(5000, ()=>{
    console.log(`Server running in 5000`)
});
