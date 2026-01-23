package com.didimo777.hs;

import com.didimo777.hs.entities.Room;
import com.didimo777.hs.repositories.RoomRepository;
import com.didimo777.hs.repositories.RoomRepositoryImpl;
import com.didimo777.hs.services.RoomService;

public class Main {
    public static void main(String[] args) {

        RoomRepository repo = new RoomRepositoryImpl();
        RoomService service = new RoomService(repo);

        service.addRoom(new Room("Single", 15000, true));

        service.getAllRooms().forEach(r ->
                System.out.println(r.getId() + " | " + r.getRoomType() + " | " + r.getPrice())
        );

        Room room = service.getRoom(1);
        System.out.println("Found room: " + room.getRoomType());
    }
}
