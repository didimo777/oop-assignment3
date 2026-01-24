package com.didimo777.hs.repositories;

import com.didimo777.hs.db.DatabaseConnection;
import com.didimo777.hs.entities.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public void create(Booking booking) {
        String sql = """
            INSERT INTO bookings (room_id, guest_id, start_date, end_date)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, booking.getRoomId());
            stmt.setInt(2, booking.getGuestId());
            stmt.setDate(3, Date.valueOf(booking.getStartDate()));
            stmt.setDate(4, Date.valueOf(booking.getEndDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create booking", e);
        }
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("guest_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Booking not found", e);
        }
    }

    @Override
    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("guest_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to load bookings", e);
        }

        return bookings;
    }

    @Override
    public List<Booking> findByGuestId(int guestId) {
        return List.of();
    }

    @Override
    public List<Booking> findByRoomId(int roomId) {
        return List.of();
    }
}