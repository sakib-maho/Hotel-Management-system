package com.sakib.hotel.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class HotelData implements Serializable {
    private final Map<Integer, Room> rooms = new LinkedHashMap<>();

    public HotelData() {
        initRooms();
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    private void initRooms() {
        for (int i = 1; i <= 10; i++) {
            rooms.put(i, new Room(i, RoomType.LUXURY_DOUBLE));
        }
        for (int i = 11; i <= 30; i++) {
            rooms.put(i, new Room(i, RoomType.DELUXE_DOUBLE));
        }
        for (int i = 31; i <= 40; i++) {
            rooms.put(i, new Room(i, RoomType.LUXURY_SINGLE));
        }
        for (int i = 41; i <= 60; i++) {
            rooms.put(i, new Room(i, RoomType.DELUXE_SINGLE));
        }
    }
}
