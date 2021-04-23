package com.prototype.Express.Class;

import java.io.StringWriter;

public class Item
{
    private String photo;
    private String _id;
    private String name;
    private String description;
    private String menu;
    private String restaurant;
    private int price;
    private String type;
    private String createdAt;
    private String updatedAt;
    private int __v;
    private int quantity;
    private int sizes[];
    private int extras[];

    public Item()
    {

    }

    public Item(String photo, String _id, String name, String description, String menu, String restaurant, int price, String type, String createdAt, String updatedAt, int __v, int quantity, int[] sizes, int[] extras)
    {
        this.photo = photo;
        this._id = _id;
        this.name = name;
        this.description = description;
        this.menu = menu;
        this.restaurant = restaurant;
        this.price = price;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
        this.quantity = quantity;
        this.sizes = sizes;
        this.extras = extras;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int[] getSizes() {
        return sizes;
    }

    public void setSizes(int[] sizes) {
        this.sizes = sizes;
    }

    public int[] getExtras() {
        return extras;
    }

    public void setExtras(int[] extras) {
        this.extras = extras;
    }
}
