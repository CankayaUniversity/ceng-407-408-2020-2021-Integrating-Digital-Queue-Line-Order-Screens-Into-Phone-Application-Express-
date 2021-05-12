const mongoose = require('mongoose');

// I added 30 minutes by default for the restaurant to prepare a meal.
const hourFromNow = function () {
  let date = new Date();
  date.setMinutes(date.getMinutes() + 30);
  let postDate = date.toLocaleString('tr-TR', {
    timeZone: 'Asia/Istanbul',
    hour12: false,
  });
  console.log(postDate);
  return postDate;
};

const OrderSchema = new mongoose.Schema({
  name: {
    type: String,
    required: [true, 'Please add a name'],
    maxlength: [100, 'Name can not be more than 50 characters'],
  },
  deadline: {
    type: Date,
    default: hourFromNow,
  },
  menuItem: {
    type: mongoose.Schema.ObjectId,
    ref: 'MenuItem',
    required: true,
  },
  user: {
    type: mongoose.Schema.ObjectId,
    ref: 'User',
    required: true,
  },
  restaurant: {
    type: mongoose.Schema.ObjectId,
    ref: 'Restaurant',
    required: true,
  },
  isActive: {
    type: Number,
    enum: [0, 1],
    default: 0,
  },
});

module.exports = mongoose.model('Order', OrderSchema);
