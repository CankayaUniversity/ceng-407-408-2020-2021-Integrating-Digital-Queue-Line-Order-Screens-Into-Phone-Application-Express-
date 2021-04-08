package com.prototype.Express.Class;

public class Restaurant
{
    private String name;
    private String address;
    private String description;
    private String ID;
    private String photo;

    public Restaurant(){
    }

    public Restaurant(String name, String address, String description, String ID, String photo) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.ID = ID;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
