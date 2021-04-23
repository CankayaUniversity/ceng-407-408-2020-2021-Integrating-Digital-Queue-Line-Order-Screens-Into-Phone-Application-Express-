package com.prototype.Express.Class;

public class Restaurant
{
    private String name;
    private String address;
    private String description;
    private String photo;
    private String key;

    public Restaurant(){
    }


    public Restaurant(String name, String address, String description, String photo, String key) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.photo = photo;
        this.key = key;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
