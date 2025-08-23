package com.glo.model;

public class Fleet {
    private int fleetId;
    private String origin;
    private String destination;
    private int noOfFleets;

    // Constructors
    public Fleet() {}

    public Fleet(int fleetId, String origin, String destination, int noOfFleets) {
        this.fleetId = fleetId;
        this.origin = origin;
        this.destination = destination;
        this.noOfFleets = noOfFleets;
    }

    // Getters and Setters
    public int getFleetId() { return fleetId; }
    public void setFleetId(int fleetId) { this.fleetId = fleetId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public int getNoOfFleets() { return noOfFleets; }
    public void setNoOfFleets(int noOfFleets) { this.noOfFleets = noOfFleets; }

    @Override
    public String toString() {
        return "Fleet{" +
                "fleetId=" + fleetId +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", noOfFleets=" + noOfFleets +
                '}';
    }
}
