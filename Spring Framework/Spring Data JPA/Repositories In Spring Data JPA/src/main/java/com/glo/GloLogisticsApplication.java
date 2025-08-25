package com.glo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GloLogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GloLogisticsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerService customerService) {
        return args -> {
            // Create customers
            Customer c1 = new Customer("Alice", "1234567890", "alice@example.com", "New York", "123 Main St");
            Customer c2 = new Customer("Bob", "0987654321", "bob@example.com", "Los Angeles", "456 Elm St");
            customerService.saveCustomer(c1);
            customerService.saveCustomer(c2);

            // Retrieve all customers
            System.out.println("All customers:");
            customerService.getAllCustomers().forEach(System.out::println);

            // Retrieve by city
            System.out.println("\nCustomers in New York:");
            customerService.getCustomersByCity("New York").forEach(System.out::println);

            // Retrieve by ID
            System.out.println("\nCustomer with ID 1:");
            customerService.getCustomerById(1L).ifPresent(System.out::println);

            // Delete a customer
            customerService.deleteCustomer(2L);
            System.out.println("\nAfter deletion:");
            customerService.getAllCustomers().forEach(System.out::println);
        };
    }
}
