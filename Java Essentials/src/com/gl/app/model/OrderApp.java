package com.gl.app.model;

public class OrderApp {
    public static void main(String[] args) {
        // declaration
        Order[] orders = new Order[2];
        // initialization
        orders[0] = new Order("12345", "PUNE", "GOA", 10.0, "John Doe", Status.SHIPPED);
        orders[1] = new Order("67890", "BENGALURU", "GOA", 20.0, "Jane Doe", Status.SHIPPED);

        // display the array values using a traditional for loop
        for (int i = 0; i < orders.length; i++) {
            Order order = orders[i];
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Origin: " + order.getOrigin());
            System.out.println("Destination: " + order.getDestination());
            System.out.println("Weight: " + order.getWeight() + " kg");
            System.out.println("Customer Name: " + order.getCustomer_Name());
            System.out.println("Status: " + order.getStatus());
            System.out.println("------------");
        }
    }
}
