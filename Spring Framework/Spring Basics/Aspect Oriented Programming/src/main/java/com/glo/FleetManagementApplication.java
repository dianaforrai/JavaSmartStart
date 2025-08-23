package com.glo;

import com.glo.model.Fleet;
import com.glo.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FleetManagementApplication implements CommandLineRunner {

    @Autowired
    private FreightService freightService;

    public static void main(String[] args) {
        SpringApplication.run(FleetManagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            System.out.println("\nFleet Management Menu:");
            System.out.println("1. View All Fleets");
            System.out.println("2. View Fleet by ID");
            System.out.println("3. Add Fleet");
            System.out.println("4. Update Fleet");
            System.out.println("5. Delete Fleet");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> freightService.viewAllFleets().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter Fleet ID: ");
                    int id = scanner.nextInt();
                    System.out.println(freightService.viewFleetById(id));
                }
                case 3 -> {
                    System.out.print("Enter Fleet ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Origin: ");
                    String origin = scanner.next();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.next();
                    System.out.print("Enter No of Fleets: ");
                    int noOfFleets = scanner.nextInt();
                    freightService.addFleet(new Fleet(id, origin, destination, noOfFleets));
                }
                case 4 -> {
                    System.out.print("Enter Fleet ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Origin: ");
                    String origin = scanner.next();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.next();
                    System.out.print("Enter No of Fleets: ");
                    int noOfFleets = scanner.nextInt();
                    freightService.updateFleet(new Fleet(id, origin, destination, noOfFleets));
                }
                case 5 -> {
                    System.out.print("Enter Fleet ID to delete: ");
                    int id = scanner.nextInt();
                    freightService.deleteFleet(id);
                }
                case 6 -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
