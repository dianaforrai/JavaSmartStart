package com.gl.app.model;

public class CustomerRepository {
    public void addCustomer(Customer customer) {
        // Logic to add customer to the repository
        System.out.println("Customer added: " + customer.getCustomerName());
    }

    public Customer getCustomerById(int custId) {
        // Logic to retrieve customer by ID from the repository
        // For demonstration, returning a dummy customer
        return new Customer(custId, "Dummy Name", "1234567890", "Dummy Address");
    }
}
