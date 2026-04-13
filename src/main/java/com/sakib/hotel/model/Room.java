package com.sakib.hotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room implements Serializable {
    private final int number;
    private final RoomType type;
    private Guest primaryGuest;
    private Guest secondaryGuest;
    private final List<FoodOrder> foodOrders = new ArrayList<>();

    public Room(int number, RoomType type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public Guest getPrimaryGuest() {
        return primaryGuest;
    }

    public Guest getSecondaryGuest() {
        return secondaryGuest;
    }

    public boolean isAvailable() {
        return primaryGuest == null;
    }

    public List<FoodOrder> getFoodOrders() {
        return Collections.unmodifiableList(foodOrders);
    }

    public void book(Guest primary, Guest secondary) {
        this.primaryGuest = primary;
        this.secondaryGuest = secondary;
    }

    public void addFoodOrder(FoodOrder order) {
        this.foodOrders.add(order);
    }

    public void checkout() {
        this.primaryGuest = null;
        this.secondaryGuest = null;
        this.foodOrders.clear();
    }
}
