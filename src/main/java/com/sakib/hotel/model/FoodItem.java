package com.sakib.hotel.model;

public enum FoodItem {
    SANDWICH(1, "Sandwich", 50),
    PASTA(2, "Pasta", 60),
    NOODLES(3, "Noodles", 70),
    COKE(4, "Coke", 30);

    private final int code;
    private final String label;
    private final double unitPrice;

    FoodItem(int code, String label, double unitPrice) {
        this.code = code;
        this.label = label;
        this.unitPrice = unitPrice;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public static FoodItem fromCode(int code) {
        for (FoodItem item : values()) {
            if (item.code == code) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid food item code: " + code);
    }
}
