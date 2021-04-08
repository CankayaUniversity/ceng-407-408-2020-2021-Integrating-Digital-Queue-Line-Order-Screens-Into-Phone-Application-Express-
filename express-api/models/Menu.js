const mongoose = require('mongoose');

const MenuSchema = new mongoose.Schema({
    name: {
        type: String,
        trim: true,
        required: [true, 'Please add a menu name']
    },
    description: {
        type: String,
        required: [true, 'Please add a description']
    },
    restaurant: {
        type: mongoose.Schema.ObjectId,
        ref: 'Restaurant',
        required: true
    },
    type: {
        type: String,
        required: [true, 'Please add menu type'],
        enum: [
            'standard',
            'special',
        ]
    },
    photo: {
        type: String,
        default: 'no-photo.jpg'
    }
}, {
    toJSON: {
        virtuals: true
    },
    toObject: {
        virtuals: true
    },
    id: false,
    timestamps: true
});


// Reverse populate with virtuals
MenuSchema.virtual('menuitems', {
    ref: 'MenuItem',
    localField: '_id',
    foreignField: 'menu',
    justOne: false
});

// Cascade delete courses when a bootcamp is deleted
MenuSchema.pre('remove', async function (next) {
    console.log(`Menuitem being removed from Menu ${this._id}`);
    await this.model('MenuItem').deleteMany({
        menu: this._id
    });
    next();
});


module.exports = mongoose.model('Menu', MenuSchema);