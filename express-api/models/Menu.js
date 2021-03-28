const mongoose = require('mongoose');

const MenuSchema = new mongoose.Schema({
    title: {
        type: String,
        trim: true,
        required: [true, 'Please add a menu title']
    },
    description: {
        type: String,
        required: [true, 'Please add a description']
    },
    restaurant: {
        type: mongoose.Schema.ObjectId,
        ref: 'Restaurant',
        required: true
    }
}, {
    timestamps: true
});


module.exports = mongoose.model('Menu', MenuSchema);