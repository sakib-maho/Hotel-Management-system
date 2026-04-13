package com.sakib.hotel;

import com.sakib.hotel.model.FoodItem;
import com.sakib.hotel.model.Guest;
import com.sakib.hotel.model.HotelData;
import com.sakib.hotel.model.Room;
import com.sakib.hotel.model.RoomType;
import com.sakib.hotel.persistence.HotelPersistence;
import com.sakib.hotel.service.HotelService;
import com.sakib.hotel.util.ConsolePrinter;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final HotelService hotelService;
    private final HotelPersistence persistence;
    private final HotelData hotelData;

    public Main() {
        this.persistence = new HotelPersistence(Path.of("backup.dat"));
        this.hotelData = persistence.loadOrCreate();
        this.hotelService = new HotelService(hotelData);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> displayRoomDetails();
                    case 2 -> displayRoomAvailability();
                    case 3 -> bookRoom();
                    case 4 -> orderFood();
                    case 5 -> checkout();
                    case 6 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        persistence.save(hotelData);
        System.out.println("Data saved. Goodbye.");
    }

    private void printMainMenu() {
        System.out.println("\nHotel Management System");
        System.out.println("1. Display room details");
        System.out.println("2. Display room availability");
        System.out.println("3. Book room");
        System.out.println("4. Order food");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
    }

    private void displayRoomDetails() {
        RoomType type = chooseRoomType();
        System.out.println("\nRoom Type: " + type);
        System.out.println("Daily Rate: " + type.getDailyRate());
        System.out.println("Double Occupancy: " + (type.isDoubleOccupancy() ? "Yes" : "No"));
    }

    private void displayRoomAvailability() {
        RoomType type = chooseRoomType();
        int count = hotelService.availabilityCount(type);
        List<Room> rooms = hotelService.availableRooms(type);
        System.out.println("Available count: " + count);
        System.out.print("Room numbers: ");
        rooms.forEach(room -> System.out.print(room.getNumber() + " "));
        System.out.println();
    }

    private void bookRoom() {
        int roomNumber = readInt("Enter room number: ");
        Guest primary = readGuest("primary");
        Guest secondary = null;
        Room room = hotelService.allRooms().get(roomNumber);
        if (room != null && room.getType().isDoubleOccupancy()) {
            System.out.print("Add second guest? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                secondary = readGuest("secondary");
            }
        }
        hotelService.bookRoom(roomNumber, primary, secondary);
        System.out.println("Room booked successfully.");
    }

    private void orderFood() {
        int roomNumber = readInt("Enter room number: ");
        ConsolePrinter.printFoodMenu();
        int foodCode = readInt("Choose food item: ");
        int quantity = readInt("Quantity: ");
        hotelService.orderFood(roomNumber, FoodItem.fromCode(foodCode), quantity);
        System.out.println("Order placed successfully.");
    }

    private void checkout() {
        int roomNumber = readInt("Enter room number: ");
        var receipt = hotelService.checkout(roomNumber);
        ConsolePrinter.printReceipt(receipt);
    }

    private RoomType chooseRoomType() {
        ConsolePrinter.showRoomTypes();
        int roomChoice = readInt("Select room type: ");
        return ConsolePrinter.roomTypeFromChoice(roomChoice);
    }

    private Guest readGuest(String label) {
        System.out.printf("Enter %s guest name: ", label);
        String name = scanner.nextLine().trim();
        System.out.printf("Enter %s guest contact: ", label);
        String contact = scanner.nextLine().trim();
        System.out.printf("Enter %s guest gender: ", label);
        String gender = scanner.nextLine().trim();
        return new Guest(name, contact, gender);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
