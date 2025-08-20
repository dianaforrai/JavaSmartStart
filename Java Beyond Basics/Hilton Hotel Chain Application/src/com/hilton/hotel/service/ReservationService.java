package com.hilton.hotel.service;

import com.hilton.hotel.model.Reservation;
import com.hilton.hotel.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReservationService {
    private RoomService roomService = new RoomService();

    public void makeReservationForRoom(Reservation reservation) throws SQLException {
        // Validate reservation
        validateReservation(reservation);

        String sql = "INSERT INTO reservation (guest_id, hotel_id, checkInDate, checkOutDate, reservationDate, status) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, reservation.getGuestId());
                pstmt.setInt(2, reservation.getHotelId());
                pstmt.setDate(3, new java.sql.Date(reservation.getCheckInDate().getTime()));
                pstmt.setDate(4, new java.sql.Date(reservation.getCheckOutDate().getTime()));
                pstmt.setDate(5, new java.sql.Date(reservation.getReservationDate().getTime()));
                pstmt.setString(6, reservation.getStatus());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Update room availability to false
                    // Note: This is simplified - in reality, you'd need room assignment logic
                    conn.commit();
                    System.out.println("Reservation made successfully!");
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                DatabaseConnection.closeConnection(conn);
            }
        }
    }

    public void cancelRoomReservation(int reservationId) throws SQLException {
        String updateReservationSql = "UPDATE reservation SET status = 'canceled' WHERE reservation_id = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // Update reservation status
            try (PreparedStatement pstmt = conn.prepareStatement(updateReservationSql)) {
                pstmt.setInt(1, reservationId);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Update room availability to true
                    // Note: This is simplified - you'd need to identify which room was reserved
                    conn.commit();
                    System.out.println("Reservation canceled successfully!");
                } else {
                    conn.rollback();
                    throw new SQLException("Reservation not found");
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                DatabaseConnection.closeConnection(conn);
            }
        }
    }

    public List<Reservation> getReservationsForHotel(int hotelId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE hotel_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getInt("guest_id"),
                            rs.getInt("hotel_id"),
                            rs.getDate("checkInDate"),
                            rs.getDate("checkOutDate"),
                            rs.getDate("reservationDate"),
                            rs.getString("status")
                    ));
                }
            }
        }
        return reservations;
    }

    private void validateReservation(Reservation reservation) throws SQLException {
        if (reservation.getCheckInDate().after(reservation.getCheckOutDate())) {
            throw new SQLException("Check-in date must be before check-out date");
        }

        if (reservation.getCheckInDate().before(new Date())) {
            throw new SQLException("Check-in date cannot be in the past");
        }
    }
}
