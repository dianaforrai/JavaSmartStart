import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ReservationService {
    private RoomService roomService = new RoomService();

    public boolean makeReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (guest_id, room_number, checkInDate, checkOutDate, reservationDate, status, hotel_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, reservation.getGuestId());
                stmt.setInt(2, reservation.getRoomNumber());
                stmt.setDate(3, Date.valueOf(reservation.getCheckInDate()));
                stmt.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
                stmt.setDate(5, Date.valueOf(reservation.getReservationDate()));
                stmt.setString(6, reservation.getStatus());
                stmt.setInt(7, reservation.getHotelId());

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Update room availability to false
                    roomService.updateRoomAvailability(reservation.getRoomNumber(),
                            reservation.getHotelId(), false);
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error making reservation: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelReservation(int reservationId) {
        String selectSql = "SELECT room_number, hotel_id FROM Reservation WHERE reservation_id = ?";
        String updateSql = "UPDATE Reservation SET status = 'cancelled' WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // First get room details
            int roomNumber = 0, hotelId = 0;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setInt(1, reservationId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    roomNumber = rs.getInt("room_number");
                    hotelId = rs.getInt("hotel_id");
                } else {
                    conn.rollback();
                    return false;
                }
            }

            // Update reservation status
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, reservationId);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Update room availability to true
                    roomService.updateRoomAvailability(roomNumber, hotelId, true);
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error cancelling reservation: " + e.getMessage());
            return false;
        }
    }

    public List<Reservation> getReservationsForHotel(int hotelId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation WHERE hotel_id = ? ORDER BY reservation_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_number"),
                        rs.getDate("checkInDate").toLocalDate(),
                        rs.getDate("checkOutDate").toLocalDate(),
                        rs.getDate("reservationDate").toLocalDate(),
                        rs.getString("status"),
                        rs.getInt("hotel_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving reservations: " + e.getMessage());
        }
        return reservations;
    }
}