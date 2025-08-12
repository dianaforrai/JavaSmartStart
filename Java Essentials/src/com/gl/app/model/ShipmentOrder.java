package com.gl.app.model;

public class ShipmentOrder {
    private String origin;
    private String destination;
    private double weight;
    public ShipmentOrder(String origin, String destination, double weight) {

        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "ShipmentOrder{" + ", origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", weight="
                + weight + '}';
    }

    public static void main(String[] args) {
        ShipmentOrder order = new ShipmentOrder("BLR", "Noida", 300.0);
        System.out.print(order);
    }
}