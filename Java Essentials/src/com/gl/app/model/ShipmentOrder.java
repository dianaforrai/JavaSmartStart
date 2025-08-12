package com.gl.app.model;

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
    public ShipmentOrder(String orderId, String origin, String destination, double weight, String customer_Name, Status status) {
        this.orderId = orderId;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.customer_Name = customer_Name;
        this.status = status;
    }
    public static void checkOrderStatus(String orderStatus) {
        switch (orderStatus.toLowerCase()) {
            case "shipped":
                System.out.println("The order is shipped.");
                break;
            case "delivered":
                System.out.println("The order is delivered.");
                break;
            default:
                System.out.println("Invalid order status.");
                break;
        }
    }

    public static void main(String[] args) {
        String orderStatus1 = "Shipped";
        String orderStatus2 = "delivered";
        checkOrderStatus(orderStatus1); // Output: The order is shipped.
        checkOrderStatus(orderStatus2); // Output: The order is delivered.
    }
}
