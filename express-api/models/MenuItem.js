const mongoose = require('mongoose');

const MenuItemSchema = mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Please add a name']
    },
    description: {
        type: String,
        maxlength: [100, 'Description can not be more than 100 characters']
    },
    price: {
        type: Number,
        required: [true, 'Please add a item cost']
    },
    menu: {
        type: mongoose.Schema.ObjectId,
        ref: 'Menu',
        required: true
    },
    type: {
        type: String,
        required: [true, 'Please add menuItem type'],
        enum: [
            'drink',
            'dessert',
            'menuitem',
            'singleitem'
        ]
    },
    photo: {
        type: String,
        default: 'no-photo.jpg'
    }
}, {
    timestamps: true
});


module.exports = mongoose.model("MenuItem", MenuItemSchema);