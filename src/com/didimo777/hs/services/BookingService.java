package com.didimo777.hs.services;

import com.didimo777.hs.entities.Booking;
import com.didimo777.hs.exceptions.NotFoundException;
import com.didimo777.hs.repositories.BookingRepository;

import java.util.List;

public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void createBooking(Booking booking) {
        bookingRepository.create(booking);
    }

    public Booking getBookingById(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new NotFoundException("Booking with id " + id + " not found");
        }
        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new NotFoundException("Booking with id " + id + " not found");
        }
    }
}