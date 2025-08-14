import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class RoomService {
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO Room (room_number, type, available, hotel_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getRoomNumber());
            stmt.setString(2, room.getType());
            stmt.setBoolean(3, room.isAvailable());
            stmt.setInt(4, room.getHotelId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
            return false;
        }
    }

    public List<Room> getRoomsForHotel(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE hotel_id = ? ORDER BY room_number";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving rooms: " + e.getMessage());
        }
        return rooms;
    }

    public Room getRoom(int roomNumber, int hotelId) {
        String sql = "SELECT * FROM Room WHERE room_number = ? AND hotel_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomNumber);
            stmt.setInt(2, hotelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving room: " + e.getMessage());
        }
        return null;
    }

    public boolean updateRoomAvailability(int roomNumber, int hotelId, boolean available) {
        String sql = "UPDATE Room SET available = ? WHERE room_number = ? AND hotel_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, available);
            stmt.setInt(2, roomNumber);
            stmt.setInt(3, hotelId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating room availability: " + e.getMessage());
            return false;
        }
    }
}