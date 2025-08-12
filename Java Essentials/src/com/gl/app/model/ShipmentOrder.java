package com.gl.app.model;

import java.util.NavigableMap;
enum Status {
    SHIPPED,
    PROCESSING,
    DELIVERED
}

public class ShipmentOrder {
    private String orderId;
    private String origin;
    private String destination;
    private double weight;
    private String customer_Name;
    private Status status;

    public ShipmentOrder(String orderId, String origin, String destination, double weight, String customer_Name, Status status) {
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public static void main(String[] args) {
        ShipmentOrder[] shippedOrders = new ShipmentOrder[2];
        ShipmentOrder[] deliveredOrders = new ShipmentOrder[2];
        ShipmentOrder order1 = new ShipmentOrder("12345", "PUNE", "GOA", 10.0, "John Doe", Status.SHIPPED);
        // ShipmentOrder order2 = new ShipmentOrder("67890", "BENGALURU", "GOA", 20.0, "Jane Doe", Status.DELIVERED);

        ShipmentOrder[] shipmentOrders = {order1};

        for (ShipmentOrder order : shipmentOrders) {
            // Check if the status is "SHIPPED" and print message
            if (order.getStatus() == Status.SHIPPED) {
                System.out.println("The order is shipped.");
            } else {
                System.out.println("The order is not shipped.");
            }
        }
    }
}