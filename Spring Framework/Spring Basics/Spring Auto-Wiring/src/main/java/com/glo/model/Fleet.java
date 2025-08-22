package com.glo.model;

public class Fleet {
    private int fleetId;
    private String fleetName;
    private int numberOfFleets;

    // Constructor for constructor-based autowiring
    public Fleet(int fleetId, String fleetName, int numberOfFleets) {
        this.fleetId = fleetId;
        this.fleetName = fleetName;
        this.numberOfFleets = numberOfFleets;
    }

    // Default constructor for byName autowiring
    public Fleet() {}

    // Getters and setters
    public int getFleetId() { return fleetId; }
    public void setFleetId(int fleetId) { this.fleetId = fleetId; }

    public String getFleetName() { return fleetName; }
    public void setFleetName(String fleetName) { this.fleetName = fleetName; }

    public int getNumberOfFleets() { return numberOfFleets; }
    public void setNumberOfFleets(int numberOfFleets) { this.numberOfFleets = numberOfFleets; }

    public void createFleetInstance() {
        System.out.println("Fleet instance created: " + fleetName);
    }
}
