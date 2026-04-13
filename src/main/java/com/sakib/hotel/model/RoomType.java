package com.sakib.hotel.model;

public enum RoomType {
    LUXURY_DOUBLE(4000, true),
    DELUXE_DOUBLE(3000, true),
    LUXURY_SINGLE(2200, false),
    DELUXE_SINGLE(1200, false);

    private final double dailyRate;
    private final boolean doubleOccupancy;

    RoomType(double dailyRate, boolean doubleOccupancy) {
        this.dailyRate = dailyRate;
        this.doubleOccupancy = doubleOccupancy;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isDoubleOccupancy() {
        return doubleOccupancy;
    }
}
