package com.glo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GloLogistic {

    public static void main(String[] args) {
        System.out.println("Starting GloLogistic Application...");

        // Load Spring context
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve Fleet bean
        Fleet fleet = context.getBean("fleet", Fleet.class);

        // Set additional properties if needed
        fleet.setDestination("San Francisco");

        // Display Fleet details
        System.out.println("Fleet Details:");
        System.out.println("Fleet ID: " + fleet.getFleetId());
        System.out.println("Number of Fleets: " + fleet.getNumberOfFleets());
        System.out.println("Origin: " + fleet.getOrigin());
        System.out.println("Destination: " + fleet.getDestination());

        // Close context to trigger destroy callbacks
        context.close();
    }
}
