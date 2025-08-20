package com.hilton.hotel.service;

import com.hilton.hotel.model.Guest;
import com.hilton.hotel.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestService {

    public void addGuest(Guest guest) throws SQLException {
        // Validate guest details
        validateGuestDetails(guest);

        String sql = "INSERT INTO guest (name, email, phone, hotel_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getEmail());
            pstmt.setString(3, guest.getPhone());
            pstmt.setInt(4, guest.getHotelId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Guest added successfully!");
            }
        }
    }

    public List<Guest> getGuestsForHotel(int hotelId) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guest WHERE hotel_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    guests.add(new Guest(
                            rs.getInt("guest_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("hotel_id")
                    ));
                }
            }
        }
        return guests;
    }

    public Guest getGuest(int guestId) throws SQLException {
        String sql = "SELECT * FROM guest WHERE guest_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, guestId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Guest(
                            rs.getInt("guest_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("hotel_id")
                    );
                }
            }
        }
        return null;
    }

    private void validateGuestDetails(Guest guest) throws SQLException {
        // Validate name
        if (guest.getName() == null || guest.getName().trim().isEmpty()) {
            throw new SQLException("Guest name cannot be empty");
        }

        // Validate email format
        if (guest.getEmail() == null || !guest.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new SQLException("Invalid email format");
        }

        // Validate phone number
        if (guest.getPhone() == null || guest.getPhone().trim().isEmpty()) {
            throw new SQLException("Phone number cannot be empty");
        }

        // Check for unique email
        if (isEmailExists(guest.getEmail())) {
            throw new SQLException("Email already exists");
        }
    }

    private boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM guest WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}