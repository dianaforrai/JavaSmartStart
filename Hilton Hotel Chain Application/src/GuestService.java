import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class GuestService {
    public boolean addGuest(Guest guest) {
        String sql = "INSERT INTO Guests (name, email, phone, hotel_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.setInt(4, guest.getHotelId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key")) {
                System.err.println("Error: Email already exists!");
            } else {
                System.err.println("Error adding guest: " + e.getMessage());
            }
            return false;
        }
    }

    public List<Guest> getGuestsForHotel(int hotelId) {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM Guests WHERE hotel_id = ? ORDER BY guest_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                guests.add(new Guest(
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("hotel_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving guests: " + e.getMessage());
        }
        return guests;
    }
}
