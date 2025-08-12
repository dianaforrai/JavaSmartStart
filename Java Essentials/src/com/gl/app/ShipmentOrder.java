package com.gl.app;

public class ShipmentOrder {
    private static int totalOrders; // Static variable to count total orders
    private int orderId; // Instance variable

    // Static initialization block
    static {
        totalOrders = 0;
    }

    public ShipmentOrder() {
        this.orderId = ++totalOrders; // Increment totalOrders and assign it to orderId
    }

    public int getOrderId() {
        return orderId;
    }

    public static int getTotalOrders() {
        return totalOrders;
    }

    public static void main(String[] args) {
        ShipmentOrder order1 = new ShipmentOrder();
        ShipmentOrder order2 = new ShipmentOrder();

        System.out.println("Order 1 ID: " + order1.getOrderId());
        System.out.println("Order 2 ID: " + order2.getOrderId());

        System.out.println("Total Orders: " + ShipmentOrder.getTotalOrders());
    }
}