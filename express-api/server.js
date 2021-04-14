const express = require('express');
const path = require('path');
const dotenv = require('dotenv');
const cors = require('cors');
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');
const errorHandler = require('./middleware/error');
const connectDB = require('./config/db');

const app = express();
const server = require('http').Server(app);
const io = require('socket.io')(server, {
  pingTimeout: 60000,
});

// Load env vars
dotenv.config({
  path: './config/config.env',
});

// Connect to database
connectDB();

const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Express API',
      version: '1.0.0',
      description:
        'Documentation for front-end and mobile developers who will use the Express API',
      contact: {
        //name: "Can Özal",
        url:
          'https://github.com/CankayaUniversity/ceng-407-408-2020-2021-Integrating-Digital-Queue-Line-Order-Screens-Into-Phone-Application-Express-/wiki',
        email: 'cannozall@gmail.com',
      },
    },
    servers: [
      {
        url: 'http://104.248.207.133:5000',
      },
    ],
  },
  apis: ['./controllers/*.js'],
};

const specs = swaggerJsDoc(options);

app.use('/api-docs', swaggerUI.serve, swaggerUI.setup(specs));

// Route files
const restaurants = require('./routes/restaurants');
const menus = require('./routes/menus');
const menuItems = require('./routes/menuItems');

// Body parser
app.use(express.json());

// Enable CORS
app.use(cors());

// Set static folder
app.use(express.static(path.join(__dirname, 'public')));

// Routes
app.use('/api/v1/restaurants', restaurants);
app.use('/api/v1/menus', menus);
app.use('/api/v1/menuitems', menuItems);

app.use('/api/v1/project-info', (req, res, next) => {
  res.json({
    project_name: 'express',
    project_members: ['can', 'suhan', 'talha'],
  });
});

app.use(errorHandler);

server.listen(5000);

io.on('connection', (socket) => {
  console.log('someone connected');
  socket.on('pc-send', (data) => {
    io.sockets.emit('phone-receive', data);
  });

  socket.on('phone-send', (data) => {
    io.sockets.emit('pc-receive', data);
  });
});
