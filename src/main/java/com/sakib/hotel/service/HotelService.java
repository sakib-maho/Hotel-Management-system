package com.sakib.hotel.service;

import com.sakib.hotel.exception.RoomNotAvailableException;
import com.sakib.hotel.exception.RoomNotFoundException;
import com.sakib.hotel.model.FoodItem;
import com.sakib.hotel.model.FoodOrder;
import com.sakib.hotel.model.Guest;
import com.sakib.hotel.model.HotelData;
import com.sakib.hotel.model.Room;
import com.sakib.hotel.model.RoomType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelService {
    private final HotelData hotelData;

    public HotelService(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public Map<Integer, Room> allRooms() {
        return hotelData.getRooms();
    }

    public List<Room> availableRooms(RoomType type) {
        return hotelData.getRooms()
                .values()
                .stream()
                .filter(room -> room.getType() == type && room.isAvailable())
                .collect(Collectors.toList());
    }

    public Room bookRoom(int roomNumber, Guest primary, Guest secondary) {
        Room room = requireRoom(roomNumber);
        if (!room.isAvailable()) {
            throw new RoomNotAvailableException("Room " + roomNumber + " is already booked.");
        }
        if (!room.getType().isDoubleOccupancy()) {
            secondary = null;
        }
        room.book(primary, secondary);
        return room;
    }

    public void orderFood(int roomNumber, FoodItem item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        Room room = requireRoom(roomNumber);
        if (room.isAvailable()) {
            throw new RoomNotAvailableException("Room " + roomNumber + " is not booked.");
        }
        room.addFoodOrder(new FoodOrder(item, quantity));
    }

    public BillReceipt checkout(int roomNumber) {
        Room room = requireRoom(roomNumber);
        if (room.isAvailable()) {
            throw new RoomNotAvailableException("Room " + roomNumber + " is already empty.");
        }

        double roomCharge = room.getType().getDailyRate();
        List<FoodOrder> orders = room.getFoodOrders();
        double foodCharge = orders.stream().mapToDouble(FoodOrder::getPrice).sum();
        double total = roomCharge + foodCharge;
        BillReceipt receipt = new BillReceipt(roomNumber, roomCharge, orders, total);
        room.checkout();
        return receipt;
    }

    public int availabilityCount(RoomType type) {
        return (int) hotelData.getRooms()
                .values()
                .stream()
                .filter(room -> room.getType() == type && room.isAvailable())
                .count();
    }

    private Room requireRoom(int roomNumber) {
        Room room = hotelData.getRooms().get(roomNumber);
        if (room == null) {
            throw new RoomNotFoundException("Room " + roomNumber + " does not exist.");
        }
        return room;
    }
}
