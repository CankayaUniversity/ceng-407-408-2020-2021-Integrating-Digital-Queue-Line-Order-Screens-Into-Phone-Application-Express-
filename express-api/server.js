const express = require('express');
const path = require('path');
const dotenv = require('dotenv');
const cors = require('cors');
const cookieParser = require('cookie-parser');

const errorHandler = require('./middleware/error');
const connectDB = require('./config/db');

const Order = require('./models/Order');
const User = require('./models/User');
const MenuItem = require('./models/MenuItem');
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

      User.findOne({ _id: payload.id }, function (err, user) {
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
  clients[socket.request.user._id] = socket.id;

  io.sockets.emit('success', {
    message: clients,
    user: socket.request.user,
  });

  socket.on(
    'pc-send',
    asyncHandler(async (data) => {
      let order = await Order.findOneAndUpdate(
        { _id: data },
        { isFinished: 1 },
        {
          new: true,
          runValidators: true,
        }
      );
      io.to(clients[order.user]).emit('phone-receive', order);
    })
  );

  socket.on(
    'phone-send',
    asyncHandler(async (data) => {
      let item = await MenuItem.findById(data.menuItem);

      data.user = socket.request.user._id;
      data.restaurant = item.restaurant;
      let order = await Order.create(data);
      order = await Order.findById(order._id)
        .populate('menuItem')
        .populate('user')
        .populate('restaurant');

      console.log(order);
      io.to(clients[order.restaurant.user]).emit('pc-receive', order);
    })
  );

  socket.on('disconnect', () => {
    delete clients[socket.request.user._id];
  });
});

app.use(errorHandler);

server.listen(5000);
