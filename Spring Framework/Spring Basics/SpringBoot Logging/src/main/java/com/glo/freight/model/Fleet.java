package com.glo.freight.model;

public class Fleet {
    private String fleetId;
    private String origin;
    private String destination;
    private String noOfFleets; // per your spec

    public Fleet() {}

    public Fleet(String fleetId, String origin, String destination, String noOfFleets) {
        this.fleetId = fleetId;
        this.origin = origin;
        this.destination = destination;
        this.noOfFleets = noOfFleets;
    }

    public String getFleetId() { return fleetId; }
    public void setFleetId(String fleetId) { this.fleetId = fleetId; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getNoOfFleets() { return noOfFleets; }
    public void setNoOfFleets(String noOfFleets) { this.noOfFleets = noOfFleets; }

    @Override
    public String toString() {
        return "Fleet{" +
                "fleetId='" + fleetId + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", noOfFleets='" + noOfFleets + '\'' +
                '}';
    }
}
