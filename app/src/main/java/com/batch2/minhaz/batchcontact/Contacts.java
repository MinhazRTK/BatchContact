package com.batch2.minhaz.batchcontact;

public class Contacts {


    int id;
    String name, phone, email, address, website, img;

    public Contacts(int id, String name, String phone, String email, String address, String website, String img) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.website = website;
        this.img = img;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public String getImg() {
        return img;
    }
}
