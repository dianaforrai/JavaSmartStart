package com.globallogic.hitachi;

import com.globallogic.hitachi.entity.Customer;
import com.globallogic.hitachi.entity.SimDetails;
import com.globallogic.hitachi.exception.CustomerDoesNotExistException;
import com.globallogic.hitachi.exception.CustomerTableEmptyException;
import com.globallogic.hitachi.exception.SIMDoesNotExistException;
import com.globallogic.hitachi.service.CustomerService;
import com.globallogic.hitachi.service.SimDetailsService;
import com.globallogic.hitachi.util.HibernateUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class HitachiMobileApplication {

    private static CustomerService customerService = new CustomerService();
    private static SimDetailsService simDetailsService = new SimDetailsService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("    Welcome to Hitachi Mobile Application    ");
        System.out.println("         SIM Management Portal               ");
        System.out.println("==============================================\n");

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    listCustomersInBangalore();
                    break;
                case 2:
                    listActiveSimDetails();
                    break;
                case 3:
                    fetchSimStatus();
                    break;
                case 4:
                    updateSimStatusToActive();
                    break;
                case 5:
                    updateCustomerAddress();
                    break;
                case 6:
                    listAllCustomers();
                    break;
                case 7:
                    System.out.println("\nThank you for using Hitachi Mobile Application!");
                    System.out.println("Goodbye!");
                    HibernateUtil.shutdown();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. List Customers in Bangalore");
        System.out.println("2. List Active SIM Details");
        System.out.println("3. Fetch SIM Status");
        System.out.println("4. Update SIM Status to Active");
        System.out.println("5. Update Customer Address");
        System.out.println("6. List All Customers");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice (1-7): ");
    }

    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listCustomersInBangalore() {
        System.out.println("\n=== CUSTOMERS IN BANGALORE ===");
        try {
            List<Customer> bangaloreCustomers = customerService.getCustomersInBangalore();

            if (bangaloreCustomers.isEmpty()) {
                System.out.println("No customers found in Bangalore.");
            } else {
                System.out.printf("%-15s %-20s %-30s %-15s %-15s%n",
                        "ID", "Name", "Email", "City", "State");
                System.out.println("-".repeat(100));

                bangaloreCustomers.forEach(customer -> {
                    System.out.printf("%-15s %-20s %-30s %-15s %-15s%n",
                            customer.getUniqueIdNumber(),
                            customer.getFirstName() + " " + customer.getLastName(),
                            customer.getEmailAddress(),
                            customer.getCity(),
                            customer.getState());
                });

                System.out.println("\nTotal customers in Bangalore: " + bangaloreCustomers.size());
            }
        } catch (Exception e) {
            System.out.println("Error fetching customers: " + e.getMessage());
        }
    }

    private static void listActiveSimDetails() {
        System.out.println("\n=== ACTIVE SIM DETAILS ===");
        try {
            List<SimDetails> activeSimDetails = simDetailsService.getActiveSimDetails();

            if (activeSimDetails.isEmpty()) {
                System.out.println("No active SIM details found.");
            } else {
                System.out.printf("%-10s %-15s %-15s %-10s%n",
                        "SIM ID", "Service Number", "SIM Number", "Status");
                System.out.println("-".repeat(60));

                activeSimDetails.forEach(sim -> {
                    System.out.printf("%-10s %-15s %-15s %-10s%n",
                            sim.getSimId(),
                            sim.getServiceNumber(),
                            sim.getSimNumber(),
                            sim.getStatus().toUpperCase());
                });

                System.out.println("\nTotal active SIM details: " + activeSimDetails.size());
            }
        } catch (Exception e) {
            System.out.println("Error fetching active SIM details: " + e.getMessage());
        }
    }

    private static void fetchSimStatus() {
        System.out.println("\n=== FETCH SIM STATUS ===");
        try {
            System.out.print("Enter SIM Number: ");
            BigInteger simNumber = new BigInteger(scanner.nextLine());

            System.out.print("Enter Service Number: ");
            BigInteger serviceNumber = new BigInteger(scanner.nextLine());

            String status = simDetailsService.fetchSimStatus(simNumber, serviceNumber);
            System.out.println("\nSIM Status: " + status.toUpperCase());

        } catch (SIMDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter valid numbers.");
        } catch (Exception e) {
            System.out.println("Error fetching SIM status: " + e.getMessage());
        }
    }

    private static void updateSimStatusToActive() {
        System.out.println("\n=== UPDATE SIM STATUS TO ACTIVE ===");
        try {
            System.out.print("Enter SIM ID: ");
            Integer simId = Integer.parseInt(scanner.nextLine());

            simDetailsService.updateSimStatusToActive(simId);
            System.out.println("Success: SIM status updated to Active successfully.");

        } catch (SIMDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid SIM ID format. Please enter a valid integer.");
        } catch (Exception e) {
            System.out.println("Error updating SIM status: " + e.getMessage());
        }
    }
    private static void updateCustomerAddress() {
        System.out.println("\n=== UPDATE CUSTOMER ADDRESS ===");
        try {
            System.out.print("Enter Customer Unique ID Number: ");
            BigInteger uniqueIdNumber = new BigInteger(scanner.nextLine());

            System.out.print("Enter New Address: ");
            String newAddress = scanner.nextLine();

            customerService.updateCustomerAddress(uniqueIdNumber, newAddress);
            System.out.println("Success: Customer address updated successfully.");

        } catch (CustomerDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Unique ID format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error updating customer address: " + e.getMessage());
        }
    }
    private static void listAllCustomers() {
        System.out.println("\n=== ALL CUSTOMERS ===");
        try {
            List<Customer> allCustomers = customerService.getAllCustomers();

            if (allCustomers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                System.out.printf("%-15s %-20s %-30s %-15s %-15s%n",
                        "ID", "Name", "Email", "City", "State");
                System.out.println("-".repeat(100));

                allCustomers.forEach(customer -> {
                    System.out.printf("%-15s %-20s %-30s %-15s %-15s%n",
                            customer.getUniqueIdNumber(),
                            customer.getFirstName() + " " + customer.getLastName(),
                            customer.getEmailAddress(),
                            customer.getCity(),
                            customer.getState());
                });

                System.out.println("\nTotal customers: " + allCustomers.size());
            }
        } catch (CustomerTableEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error fetching customers: " + e.getMessage());
        }
    }
}
