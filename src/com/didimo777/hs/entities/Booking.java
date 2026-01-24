package com.didimo777.hs.entities;

import java.time.LocalDate;

public class Booking {

    private int id;
    private int roomId;
    private int guestId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(int id, int roomId, int guestId,
                   LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getGuestId() {
        return guestId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}