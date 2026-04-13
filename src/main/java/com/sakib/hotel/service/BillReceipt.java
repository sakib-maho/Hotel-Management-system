package com.sakib.hotel.service;

import com.sakib.hotel.model.FoodOrder;

import java.util.List;

public record BillReceipt(
        int roomNumber,
        double roomCharge,
        List<FoodOrder> orders,
        double total
) {
}
