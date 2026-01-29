package com.didimo777.hs.repositories;

import com.didimo777.hs.entities.Guest;
import java.util.List;

public interface GuestRepository {
    void create(Guest guest);
    Guest findById(int id);
    List<Guest> findAll();
}
