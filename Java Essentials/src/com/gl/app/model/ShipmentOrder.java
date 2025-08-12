package com.gl.app.model;

enum Status {
    SHIPPED,
    PROCESSING,
    DELIVERED
}

class Order {
    private String orderId;
    private String origin;
    private String destination;
    private double weight;
    private String customer_Name;
    private Status status;

    public Order(String orderId, String origin, String destination, double weight, String customer_Name, Status status) {
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

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public Status getStatus() {
        return status;
    }
}

