package com.glo.controller;

import com.glo.model.Fleet;
import com.glo.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class FleetController {

    @Autowired
    private FreightService freightService;

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFleet Management Menu:");
            System.out.println("1. View All Fleets");
            System.out.println("2. Add Fleet");
            System.out.println("3. Update Fleet");
            System.out.println("4. Delete Fleet");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    List<Fleet> fleets = freightService.getAllFleets();
                    fleets.forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Enter Fleet ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Origin: ");
                    String origin = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter No of Fleets: ");
                    String noOfFleets = scanner.nextLine();
                    System.out.print("Enter Weight: ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    freightService.addFleet(new Fleet(id, origin, destination, noOfFleets, weight));
                }
                case 3 -> {
                    System.out.print("Enter Fleet ID to update: ");
                    String id = scanner.nextLine();
                    Fleet existing = freightService.getFleetById(id);
                    if (existing != null) {
                        System.out.print("Enter new Origin: ");
                        existing.setOrigin(scanner.nextLine());
                        System.out.print("Enter new Destination: ");
                        existing.setDestination(scanner.nextLine());
                        System.out.print("Enter new No of Fleets: ");
                        existing.setNoOfFleets(scanner.nextLine());
                        System.out.print("Enter new Weight: ");
                        existing.setWeight(scanner.nextDouble());
                        scanner.nextLine();
                        freightService.updateFleet(existing);
                    } else {
                        System.out.println("Fleet not found!");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Fleet ID to delete: ");
                    String id = scanner.nextLine();
                    freightService.deleteFleet(id);
                }
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }
}
