const express = require('express');
const path = require('path');
const dotenv = require('dotenv');
const cors = require('cors');
const cookieParser = require('cookie-parser');
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUI = require('swagger-ui-express');
const errorHandler = require('./middleware/error');
const connectDB = require('./config/db');

const Order = require('./models/Order');
const User = require('./models/User');
const ErrorResponse = require('./utils/errorResponse');
const asyncHandler = require('./middleware/async');

const app = express();
const server = require('http').Server(app);

const io = require('socket.io')(server, {
  pingTimeout: 60000,
});
var jwtAuth = require('socketio-jwt-auth');

app.set('views', 'views');
app.set('view engine', 'ejs');

app.use(express.static(path.join(__dirname, 'public')));

app.use('/socket', (req, res, next) => {
  res.render('info/socket-info');
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
const orders = require('./routes/orders');
const auth = require('./routes/auth');

// Body parser
app.use(express.json());

// Cookie parser
app.use(cookieParser());

// Enable CORS
app.use(cors());

// Set static folder
app.use(express.static(path.join(__dirname, 'public')));

// Routes
app.use('/api/v1/restaurants', restaurants);
app.use('/api/v1/menus', menus);
app.use('/api/v1/menuitems', menuItems);
app.use('/api/v1/orders', orders);
app.use('/api/v1/auth', auth);

app.use('/api/v1/project-info', (req, res, next) => {
  res.json({
    project_name: 'express',
    project_members: ['can', 'suhan', 'talha'],
  });
});

io.use(
  jwtAuth.authenticate(
    {
      secret: process.env.JWT_SECRET, // required, used to verify the token's signature
    },
    function (payload, done) {
      // done is a callback, you can use it as follows
      User.findOne({ id: payload.sub }, function (err, user) {
        if (err) {
          // return error
          return done(err);
        }
        if (!user) {
          // return fail with an error message
          return done(null, false, 'user does not exist');
        }
        // return success with a user info
        return done(null, user);
      });
    }
  )
);

let clients = {};

io.on('connection', (socket) => {
  //io.sockets.emit('user', { data: socket.id });

  clients[socket.request.user._id] = socket.id;

  socket.emit('success', {
    message: 'success logged in!',
    user: socket.request.user,
  });

  socket.on('pc-send', (data) => {
    io.sockets.emit('phone-receive', data);
  });

  socket.on(
    'phone-send',
    asyncHandler(async (data) => {
      const orders = await Order.create(data);

      socket.name = socket.id;

      // res.status(200).json({
      //   success: true,
      //   data: orders,
      // });

      io.sockets.emit('pc-receive', orders);
    })
  );
});

app.use(errorHandler);

// global.clients = clients;
// exports.io = io;

server.listen(5000);
