package com.techlogics.parking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ParkingData {

    @JsonProperty("parkingAreaId")
    private String parkingAreaId;

    @JsonProperty("availableSpaces")
    private int availableSpaces;

    @JsonProperty("totalSpaces")
    private int totalSpaces;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonProperty("location")
    private String location;

    // Default constructor
    public ParkingData() {
    }

    // Constructor
    public ParkingData(String parkingAreaId, int availableSpaces, int totalSpaces, String location) {
        this.parkingAreaId = parkingAreaId;
        this.availableSpaces = availableSpaces;
        this.totalSpaces = totalSpaces;
        this.location = location;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getParkingAreaId() {
        return parkingAreaId;
    }

    public void setParkingAreaId(String parkingAreaId) {
        this.parkingAreaId = parkingAreaId;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public int getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ParkingData{" +
                "parkingAreaId='" + parkingAreaId + '\'' +
                ", availableSpaces=" + availableSpaces +
                ", totalSpaces=" + totalSpaces +
                ", timestamp=" + timestamp +
                ", location='" + location + '\'' +
                '}';
    }
}