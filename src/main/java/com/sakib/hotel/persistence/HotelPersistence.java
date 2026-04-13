package com.sakib.hotel.persistence;

import com.sakib.hotel.model.HotelData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class HotelPersistence {
    private final Path filePath;

    public HotelPersistence(Path filePath) {
        this.filePath = filePath;
    }

    public HotelData loadOrCreate() {
        if (!Files.exists(filePath)) {
            return new HotelData();
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (HotelData) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HotelData();
        }
    }

    public void save(HotelData data) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            output.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save hotel data", e);
        }
    }
}
