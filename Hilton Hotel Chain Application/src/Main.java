import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Hotel {
    private int hotelId;
    private String name;
    private String location;

    public Hotel(int hotelId, String name, String location) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
    }

    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("Hotel ID: %d | Name: %s | Location: %s", hotelId, name, location);
    }
}

class Guest {
    private int guestId;
    private String name;
    private String email;
    private String phone;
    private int hotelId;

    public Guest(int guestId, String name, String email, String phone, int hotelId) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hotelId = hotelId;
    }

    // Getters and Setters
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return String.format("Guest ID: %d | Name: %s | Email: %s | Phone: %s | Hotel ID: %d",
                guestId, name, email, phone, hotelId);
    }
}
class Room {
    private int roomNumber;
    private String type;
    private boolean available;
    private int hotelId;

    public Room(int roomNumber, String type, boolean available, int hotelId) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = available;
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return String.format("Room: %d | Type: %s | Available: %s | Hotel ID: %d",
                roomNumber, type, available ? "Yes" : "No", hotelId);
    }
}