const mongoose = require('mongoose');

const MenuItemSchema = mongoose.Schema(
  {
    name: {
      type: String,
      required: [true, 'Please add a name'],
    },
    description: {
      type: String,
      maxlength: [100, 'Description can not be more than 100 characters'],
    },
    menu: {
      type: mongoose.Schema.ObjectId,
      ref: 'Menu',
      required: true,
    },
    restaurant: {
      type: mongoose.Schema.ObjectId,
      ref: 'Restaurant',
      required: true,
    },
    type: {
      type: String,
      required: [true, 'Please add menuItem type'],
      enum: ['drink', 'dessert', 'menuitem', 'singleitem'],
    },
    photo: {
      type: String,
      default: 'no-photo.jpg',
    },
    price: {
      type: Number,
      required: [true, 'Please add price'],
      min: 0,
    },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model('MenuItem', MenuItemSchema);
