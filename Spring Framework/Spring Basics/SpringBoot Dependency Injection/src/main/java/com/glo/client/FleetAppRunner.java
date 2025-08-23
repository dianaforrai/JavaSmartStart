package com.glo.client;

import com.glo.model.Vehicle;
import com.glo.service.FleetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class FleetAppRunner implements CommandLineRunner {

    private final FleetService fleetService;

    public FleetAppRunner(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FleetAppRunner.class, args);
    }

    @Override
    public void run(String... args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.glo.model");
        context.refresh();

        // Prototype Vehicle instances
        Vehicle v1 = context.getBean(Vehicle.class);
        v1.setId(1L); v1.setMake("Tesla"); v1.setModel("Model X"); v1.setYear(2023); v1.setMileage(1000);

        Vehicle v2 = context.getBean(Vehicle.class);
        v2.setId(2L); v2.setMake("Ford"); v2.setModel("F-150"); v2.setYear(2022); v2.setMileage(5000);

        fleetService.addVehicle(v1);
        fleetService.addVehicle(v2);

        // List vehicles
        fleetService.getAllVehicles().forEach(System.out::println);

        context.close();
    }
}
