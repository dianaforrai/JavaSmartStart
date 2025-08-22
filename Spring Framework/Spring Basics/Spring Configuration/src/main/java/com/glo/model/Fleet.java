package com.glo.model;

public class Fleet {
    private long fleetId;
    private String origin;
    private String destination;
    private int requiredFleets;

    // Constructors
    public Fleet() {}

    public Fleet(long fleetId, String origin, String destination, int requiredFleets) {
        this.fleetId = fleetId;
        this.origin = origin;
        this.destination = destination;
        this.requiredFleets = requiredFleets;
    }

    // Getters & Setters
    public long getFleetId() { return fleetId; }
    public void setFleetId(long fleetId) { this.fleetId = fleetId; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getRequiredFleets() { return requiredFleets; }
    public void setRequiredFleets(int requiredFleets) { this.requiredFleets = requiredFleets; }
}
