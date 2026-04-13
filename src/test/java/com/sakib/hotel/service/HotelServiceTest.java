package com.sakib.hotel.service;

import com.sakib.hotel.exception.RoomNotAvailableException;
import com.sakib.hotel.model.FoodItem;
import com.sakib.hotel.model.Guest;
import com.sakib.hotel.model.HotelData;
import com.sakib.hotel.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HotelServiceTest {
    private HotelService service;

    @BeforeEach
    void setUp() {
        service = new HotelService(new HotelData());
    }

    @Test
    void shouldBookRoomAndReduceAvailability() {
        int before = service.availabilityCount(RoomType.LUXURY_DOUBLE);
        service.bookRoom(1, new Guest("A", "111", "M"), null);
        int after = service.availabilityCount(RoomType.LUXURY_DOUBLE);
        assertEquals(before - 1, after);
    }

    @Test
    void shouldRejectBookingAlreadyBookedRoom() {
        service.bookRoom(1, new Guest("A", "111", "M"), null);
        assertThrows(RoomNotAvailableException.class, () ->
                service.bookRoom(1, new Guest("B", "222", "F"), null));
    }

    @Test
    void shouldPlaceFoodOrderAndIncludeInReceipt() {
        service.bookRoom(31, new Guest("A", "111", "M"), null);
        service.orderFood(31, FoodItem.COKE, 2);
        BillReceipt receipt = service.checkout(31);

        assertEquals(2200.0, receipt.roomCharge());
        assertEquals(1, receipt.orders().size());
        assertEquals(2260.0, receipt.total());
    }
}
