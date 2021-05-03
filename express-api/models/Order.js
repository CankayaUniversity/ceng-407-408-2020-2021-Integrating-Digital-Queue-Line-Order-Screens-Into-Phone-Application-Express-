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
  size: {
    type: String,
    maxlength: [100, 'Size can not be more than 50 characters'],
    default: 'default',
  },
  extra: {
    type: String,
    maxlength: [100, 'Extra can not be more than 50 characters'],
  },
});

module.exports = mongoose.model('Order', OrderSchema);
