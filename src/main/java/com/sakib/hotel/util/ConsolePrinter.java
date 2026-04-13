package com.sakib.hotel.util;

import com.sakib.hotel.model.FoodItem;
import com.sakib.hotel.model.RoomType;
import com.sakib.hotel.service.BillReceipt;

public final class ConsolePrinter {
    private ConsolePrinter() {
    }

    public static void showRoomTypes() {
        System.out.println("\nChoose room type:");
        System.out.println("1. Luxury Double Room");
        System.out.println("2. Deluxe Double Room");
        System.out.println("3. Luxury Single Room");
        System.out.println("4. Deluxe Single Room");
    }

    public static RoomType roomTypeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> RoomType.LUXURY_DOUBLE;
            case 2 -> RoomType.DELUXE_DOUBLE;
            case 3 -> RoomType.LUXURY_SINGLE;
            case 4 -> RoomType.DELUXE_SINGLE;
            default -> throw new IllegalArgumentException("Invalid room type choice.");
        };
    }

    public static void printFoodMenu() {
        System.out.println("\nFood Menu:");
        for (FoodItem item : FoodItem.values()) {
            System.out.printf("%d. %s (%.0f)%n", item.getCode(), item.getLabel(), item.getUnitPrice());
        }
    }

    public static void printReceipt(BillReceipt receipt) {
        System.out.println("\n--- Bill Receipt ---");
        System.out.println("Room Number: " + receipt.roomNumber());
        System.out.printf("Room Charge: %.2f%n", receipt.roomCharge());
        if (!receipt.orders().isEmpty()) {
            System.out.println("Food Charges:");
            for (var order : receipt.orders()) {
                System.out.printf("- %s x%d = %.2f%n",
                        order.getItem().getLabel(),
                        order.getQuantity(),
                        order.getPrice());
            }
        }
        System.out.printf("Total: %.2f%n", receipt.total());
        System.out.println("--------------------");
    }
}
