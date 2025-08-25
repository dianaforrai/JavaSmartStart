package com.glo;

import com.glo.model.Customer;
import com.glo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerAppRunner implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) {

        // Register valid customer
        Customer customer1 = new Customer("Alice", "1234567890", "alice@example.com", "New York", "123 Main St");
        Customer registeredCustomer1 = customerService.registerCustomer(customer1);

        // Attempt to register invalid customer
        Customer customer2 = new Customer("", "123", "invalidEmail", "Los Angeles", "456 Elm St");
        Customer registeredCustomer2 = customerService.registerCustomer(customer2);

        // Retrieve customer by ID
        registeredCustomer1.ifPresent(c -> System.out.println("Customer retrieved: " + c));

        // Retrieve all customers in New York
        customerService.getCustomersByCity("New York")
                .forEach(c -> System.out.println("Customer in NY: " + c));

        // Delete customer
        customerService.deleteCustomer(registeredCustomer1.getCustId());
    }
}
