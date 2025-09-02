package com.techlogics.parking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_history")
public class ParkingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_area_id", nullable = false)
    private String parkingAreaId;

    @Column(name = "available_spaces", nullable = false)
    private Integer availableSpaces;

    @Column(name = "total_spaces", nullable = false)
    private Integer totalSpaces;

    @Column(name = "location")
    private String location;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Default constructor
    public ParkingEntity() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor
    public ParkingEntity(String parkingAreaId, Integer availableSpaces, Integer totalSpaces, String location, LocalDateTime timestamp) {
        this.parkingAreaId = parkingAreaId;
        this.availableSpaces = availableSpaces;
        this.totalSpaces = totalSpaces;
        this.location = location;
        this.timestamp = timestamp;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingAreaId() {
        return parkingAreaId;
    }

    public void setParkingAreaId(String parkingAreaId) {
        this.parkingAreaId = parkingAreaId;
    }

    public Integer getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(Integer availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}