package com.hilton.hotel.service;

import com.hilton.hotel.model.Room;
import com.hilton.hotel.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    public void addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO room (room_number, type, available, hotel_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, room.getRoomNumber());
            pstmt.setString(2, room.getType());
            pstmt.setBoolean(3, room.isAvailable());
            pstmt.setInt(4, room.getHotelId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room added successfully!");
            }
        }
    }

    public List<Room> getRoomsForHotel(int hotelId) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE hotel_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("room_number"),
                            rs.getString("type"),
                            rs.getBoolean("available"),
                            rs.getInt("hotel_id")
                    ));
                }
            }
        }
        return rooms;
    }

    public Room getRoom(int roomId) throws SQLException {
        String sql = "SELECT * FROM room WHERE room_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("room_number"),
                            rs.getString("type"),
                            rs.getBoolean("available"),
                            rs.getInt("hotel_id")
                    );
                }
            }
        }
        return null;
    }

    public List<Room> getAvailableRoomsForHotel(int hotelId) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE hotel_id = ? AND available = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("room_number"),
                            rs.getString("type"),
                            rs.getBoolean("available"),
                            rs.getInt("hotel_id")
                    ));
                }
            }
        }
        return rooms;
    }

    public void updateRoomAvailability(int roomNumber, boolean available) throws SQLException {
        String sql = "UPDATE room SET available = ? WHERE room_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, available);
            pstmt.setInt(2, roomNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room availability updated successfully!");
            }
        }
    }
}
