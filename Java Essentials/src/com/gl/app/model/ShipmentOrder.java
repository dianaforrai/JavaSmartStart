package com.gl.app.model;

import java.util.NavigableMap;

public class ShipmentOrder {
    private String orderId;
    private String origin;
    private String destination;
    private double weight;
    private String customer_Name;
    private String status;
    public ShipmentOrder(String origin, String destination, double weight) {

        this.orderId = orderId;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.customer_Name = customer_Name;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
    public String getCustomer_Name() {
        return customer_Name;
    }
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShipmentOrder{" + ", origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", weight="
                + weight + '}';
    }

    public static void main(String[] args) {
      int[] orderIds = {123, 456, 789};
      String[] shipmentDestination = {"GOA", "MUMBAI", "MANGALORE"};
        double[] shipmentWeight = {10.0, 20.0, 30.0};
        System.out.println("----- Processing orders with for loop -----");

        for(int i= 0; i < orderIds.length; i++) {
            int orderId = orderIds[i];
            String destination = shipmentDestination[i];
            double weight = shipmentWeight[i];
            System.out.println("Processing order ID: " + orderId + ", Destination: " + destination + ", Weight: " + weight);
        }
    }
}