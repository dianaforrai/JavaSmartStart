package com.glo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class GloLogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GloLogisticsApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            CustomerService customerService = ctx.getBean(CustomerService.class);
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println("\n=== Customer Management Menu ===");
                System.out.println("1. Add Customer");
                System.out.println("2. View Customer by ID");
                System.out.println("3. View All Customers");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Contact No: ");
                        String contact = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("City: ");
                        String city = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        Customer newCustomer = new Customer(name, contact, email, city, address);
                        customerService.saveCustomer(newCustomer);
                        System.out.println("Customer added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Customer ID: ");
                        Long id = scanner.nextLong();
                        Optional<Customer> customer = customerService.getCustomerById(id);
                        System.out.println(customer.orElse(null));
                        break;

                    case 3:
                        List<Customer> customers = customerService.getAllCustomers();
                        customers.forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Enter Customer ID to delete: ");
                        Long deleteId = scanner.nextLong();
                        customerService.deleteCustomer(deleteId);
                        System.out.println("Customer deleted successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            } while (choice != 5);

            scanner.close();
        };
    }
}
