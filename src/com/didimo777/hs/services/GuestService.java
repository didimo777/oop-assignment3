package com.didimo777.hs.services;

import com.didimo777.hs.entities.Guest;
import com.didimo777.hs.exceptions.ValidationException;
import com.didimo777.hs.repositories.GuestRepository;

import java.util.List;

public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public void addGuest(Guest guest) {
        if (guest.getName() == null || guest.getName().isEmpty())
            throw new ValidationException("Guest name is required");

        if (guest.getEmail() == null || guest.getEmail().isEmpty())
            throw new ValidationException("Guest email is required");

        repository.create(guest);
    }

    public Guest getGuest(int id) {
        return repository.findById(id);
    }

    public List<Guest> getAllGuests() {
        return repository.findAll();
    }
}
