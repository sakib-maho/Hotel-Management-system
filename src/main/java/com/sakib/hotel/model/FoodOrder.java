package com.sakib.hotel.model;

import java.io.Serializable;

public class FoodOrder implements Serializable {
    private final FoodItem item;
    private final int quantity;

    public FoodOrder(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public FoodItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return item.getUnitPrice() * quantity;
    }
}
