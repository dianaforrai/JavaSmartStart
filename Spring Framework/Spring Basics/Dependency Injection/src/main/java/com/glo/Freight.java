package com.glo;

public class Freight {
    private String freightType;
    private double weight;

    // Constructor for constructor injection
    public Freight(String freightType, double weight) {
        this.freightType = freightType;
        this.weight = weight;
    }

    public void displayFreightInfo() {
        System.out.println("Freight Type: " + freightType + ", Weight: " + weight);
    }
}
