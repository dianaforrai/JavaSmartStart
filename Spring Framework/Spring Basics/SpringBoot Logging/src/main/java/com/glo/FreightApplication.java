package com.glo;

import com.glo.freight.model.Fleet;
import com.glo.freight.service.FreightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
// Scan both com.glo.freight... and com.glo.freightServices...
@ComponentScan(basePackages = {"com.glo.freight", "com.glo.freightServices", "com.glo"})
public class FreightApplication implements CommandLineRunner {

    private static final Logger log = LogManager.getLogger(FreightApplication.class);

    private final FreightService freightService;

    public FreightApplication(FreightService freightService) {
        this.freightService = freightService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FreightApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.debug("Application started - entering menu loop");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> viewAll();
                case "2" -> viewById(scanner);
                case "3" -> add(scanner);
                case "4" -> update(scanner);
                case "5" -> delete(scanner);
                case "0" -> {
                    log.debug("Exiting application by user request.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("""
                ==== Fleet Management ====
                1) View all fleets
                2) View fleet by ID
                3) Add fleet
                4) Update fleet
                5) Delete fleet
                0) Exit
                """);
    }

    private void viewAll() {
        List<Fleet> fleets = freightService.viewAllFleets();
        if (fleets.isEmpty()) {
            System.out.println("No fleets available.");
        } else {
            fleets.forEach(System.out::println);
        }
    }

    private void viewById(Scanner scanner) {
        System.out.print("Enter Fleet ID: ");
        String id = scanner.nextLine().trim();
        Optional<Fleet> found = freightService.viewFleetById(id);
        System.out.println(found.map(Fleet::toString).orElse("Fleet not found."));
    }

    private void add(Scanner scanner) {
        Fleet f = readFleet(scanner, true);
        freightService.addFleet(f);
        System.out.println("Fleet added.");
    }

    private void update(Scanner scanner) {
        Fleet f = readFleet(scanner, false);
        boolean ok = freightService.updateFleet(f);
        System.out.println(ok ? "Fleet updated." : "Fleet not found.");
    }

    private void delete(Scanner scanner) {
        System.out.print("Enter Fleet ID to delete: ");
        String id = scanner.nextLine().trim();
        boolean ok = freightService.deleteFleet(id);
        System.out.println(ok ? "Fleet deleted." : "Fleet not found.");
    }

    private Fleet readFleet(Scanner scanner, boolean requireNewId) {
        String id;
        if (requireNewId) {
            System.out.print("Fleet ID: ");
            id = scanner.nextLine().trim();
        } else {
            System.out.print("Fleet ID to update: ");
            id = scanner.nextLine().trim();
        }
        System.out.print("Origin: ");
        String origin = scanner.nextLine().trim();
        System.out.print("Destination: ");
        String destination = scanner.nextLine().trim();
        System.out.print("No Of Fleets: ");
        String noOfFleets = scanner.nextLine().trim();
        return new Fleet(id, origin, destination, noOfFleets);
    }
}
