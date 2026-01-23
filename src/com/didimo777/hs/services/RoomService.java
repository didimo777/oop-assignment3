package com.didimo777.hs.services;

import com.didimo777.hs.entities.Room;
import com.didimo777.hs.repositories.RoomRepository;

import java.util.List;

public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public void addRoom(Room room) {
        if (room.getPrice() <= 0)
            throw new IllegalArgumentException("Price must be positive");
        repository.create(room);
    }

    public Room getRoom(int id) {
        return repository.findById(id);
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }
}

