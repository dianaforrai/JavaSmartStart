import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class HotelService {
    public boolean addHotel(Hotel hotel) {
        String sql = "INSERT INTO Hotel (name, location) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getLocation());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding hotel: " + e.getMessage());
            return false;
        }
    }

    public Hotel getHotel(int hotelId) {
        String sql = "SELECT * FROM Hotel WHERE hotel_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving hotel: " + e.getMessage());
        }
        return null;
    }
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM Hotel ORDER BY hotel_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving hotels: " + e.getMessage());
        }
        return hotels;
    }
}