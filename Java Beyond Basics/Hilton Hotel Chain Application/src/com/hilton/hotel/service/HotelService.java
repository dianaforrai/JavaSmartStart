package com.hilton.hotel.service;

import com.hilton.hotel.model.Hotel;
import com.hilton.hotel.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    public void addHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hotel (name, location) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getLocation());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hotel added successfully!");
            }
        }
    }

    public Hotel getHotel(int hotelId) throws SQLException {
        String sql = "SELECT * FROM hotel WHERE hotel_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Hotel(
                            rs.getInt("hotel_id"),
                            rs.getString("name"),
                            rs.getString("location")
                    );
                }
            }
        }
        return null;
    }

    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotel";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }
        }
        return hotels;
    }

    public List<Hotel> getHotelsWithAvailableRooms() throws SQLException {
        return getAllHotels().stream()
                .filter(hotel -> {
                    try {
                        return hasAvailableRooms(hotel.getHotelId());
                    } catch (SQLException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    private boolean hasAvailableRooms(int hotelId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM room WHERE hotel_id = ? AND available = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
