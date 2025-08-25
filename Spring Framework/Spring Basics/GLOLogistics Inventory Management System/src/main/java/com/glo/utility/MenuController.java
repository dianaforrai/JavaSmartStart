package com.glo.utility;

import com.glo.model.Product;
import com.glo.model.Customer;
import com.glo.model.Order;
import com.glo.service.ProductService;
import com.glo.service.CustomerService;
import com.glo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class MenuController implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== GLOLogistics Inventory Management System ===");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        customerMenu();
                        break;
                    case 2:
                        adminMenu();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Thank you for using GLOLogistics IMS!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Customer Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void customerMenu() {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. View All Products");
            System.out.println("2. Generate Order");
            System.out.println("3. See Order History");
            System.out.println("4. Delete Order (if not approved)");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productService.viewAllProducts();
                    break;
                case 2:
                    generateOrderFlow();
                    break;
                case 3:
                    orderService.viewAllOrders();
                    break;
                case 4:
                    deleteOrderFlow();
                    break;
                case 5:
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void adminMenu() {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add New Product");
            System.out.println("2. Update Product Quantity");
            System.out.println("3. Approve/Reject Order Request");
            System.out.println("4. See All Order Requests");
            System.out.println("5. Add New Customer");
            System.out.println("6. View All Customers");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProductFlow();
                    break;
                case 2:
                    updateProductFlow();
                    break;
                case 3:
                    approveRejectOrderFlow();
                    break;
                case 4:
                    orderService.viewAllOrders();
                    break;
                case 5:
                    addCustomerFlow();
                    break;
                case 6:
                    customerService.viewAllCustomers();
                    break;
                case 7:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addProductFlow() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        BigDecimal price = scanner.nextBigDecimal();

        System.out.print("Enter initial stock quantity: ");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(name, description, price, quantity);
        Product savedProduct = productService.addProduct(product);

        System.out.println("Product added successfully: " + savedProduct);
    }

    private void updateProductFlow() {
        System.out.print("Enter product ID to update: ");
        Long productId = scanner.nextLong();

        var productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            System.out.println("Current product: " + product);

            System.out.print("Enter new quantity: ");
            Integer newQuantity = scanner.nextInt();

            product.setProductQuantityInStock(newQuantity);
            Product updatedProduct = productService.updateProduct(product);

            System.out.println("Product updated: " + updatedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void addCustomerFlow() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, contact, address);
        Customer savedCustomer = customerService.addCustomer(customer);

        System.out.println("Customer added successfully: " + savedCustomer);
    }

    private void generateOrderFlow() {
        productService.viewAllProducts();

        System.out.print("Enter product ID to order: ");
        Long productId = scanner.nextLong();

        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();

        System.out.print("Enter quantity: ");
        Integer quantity = scanner.nextInt();

        try {
            Order order = orderService.generateOrder(productId, customerId, quantity);
            System.out.println("Order generated successfully: " + order);
        } catch (RuntimeException e) {
            System.out.println("Failed to generate order: " + e.getMessage());
        }
    }

    private void approveRejectOrderFlow() {
        orderService.viewAllOrders();

        System.out.print("Enter order ID to approve/reject: ");
        Long orderId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter new status (APPROVED/REJECTED): ");
        String status = scanner.nextLine();

        try {
            Order updatedOrder = orderService.updateOrder(orderId, status);
            System.out.println("Order updated: " + updatedOrder);
        } catch (RuntimeException e) {
            System.out.println("Failed to update order: " + e.getMessage());
        }
    }

    private void deleteOrderFlow() {
        System.out.print("Enter order ID to delete: ");
        Long orderId = scanner.nextLong();

        try {
            orderService.deleteOrder(orderId);
            System.out.println("Order deleted successfully.");
        } catch (Exception e) {
            System.out.println("Failed to delete order: " + e.getMessage());
        }
    }
}