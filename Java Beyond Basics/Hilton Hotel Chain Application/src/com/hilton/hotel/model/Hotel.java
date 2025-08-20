package com.hilton.hotel.model;

public class Hotel {
    private int hotelId;
    private String name;
    private String location;

    // Constructors
    public Hotel() {}

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Hotel(int hotelId, String name, String location) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
