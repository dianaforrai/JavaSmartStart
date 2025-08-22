package com.glo;

import com.glo.controller.FleetController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FleetApplication implements CommandLineRunner {

    @Autowired
    private FleetController fleetController;

    public static void main(String[] args) {
        SpringApplication.run(FleetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fleetController.startMenu();
    }
}
