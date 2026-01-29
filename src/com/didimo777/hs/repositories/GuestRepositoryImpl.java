package com.didimo777.hs.repositories;

import com.didimo777.hs.db.DatabaseConnection;
import com.didimo777.hs.entities.Guest;
import com.didimo777.hs.exceptions.DatabaseException;
import com.didimo777.hs.exceptions.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryImpl implements GuestRepository {

    @Override
    public void create(Guest guest) {
        String sql = "INSERT INTO guests (name, email) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, guest.getName());
            st.setString(2, guest.getEmail());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to create guest", e);
        }
    }

    @Override
    public Guest findById(int id) {
        String sql = "SELECT * FROM guests WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next())
                throw new NotFoundException("Guest not found");

            return new Guest(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

        } catch (SQLException e) {
            throw new DatabaseException("Failed to find guest", e);
        }
    }

    @Override
    public List<Guest> findAll() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guests";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                guests.add(new Guest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }

            return guests;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to load guests", e);
        }
    }
}
