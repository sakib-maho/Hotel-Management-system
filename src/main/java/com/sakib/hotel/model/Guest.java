package com.sakib.hotel.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private final String name;
    private final String contact;
    private final String gender;

    public Guest(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getGender() {
        return gender;
    }
}
