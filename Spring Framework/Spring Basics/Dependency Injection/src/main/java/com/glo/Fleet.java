package com.glo;

public class Fleet {
    private int fleetCount;
    private String fleetName;

    // Constructor for Constructor Injection
    public Fleet(int fleetCount, String fleetName) {
        this.fleetCount = fleetCount;
        this.fleetName = fleetName;
    }

    // Default constructor for Setter Injection
    public Fleet() {}

    // Getter and Setter for Setter Injection
    public int getFleetCount() {
        return fleetCount;
    }

    public void setFleetCount(int fleetCount) {
        this.fleetCount = fleetCount;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public void displayFleetInfo() {
        System.out.println("Fleet Name: " + fleetName + ", Fleet Count: " + fleetCount);
    }
}
