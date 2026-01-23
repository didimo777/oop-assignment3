package com.didimo777.hs.repositories;

import com.didimo777.hs.entities.Room;
import java.util.List;

public interface RoomRepository {
    void create(Room room);
    Room findById(int id);
    List<Room> findAll();
}

