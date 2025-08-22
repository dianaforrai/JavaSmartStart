package com.glo.client;

import com.glo.model.Fleet;
import com.glo.service.FleetService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Constructor autowiring
        Fleet fleet = (Fleet) context.getBean("fleet");
        System.out.println("Fleet Name (constructor): " + fleet.getFleetName());

        // Constructor autowired service
        FleetService fleetService = (FleetService) context.getBean("fleetService");
        fleetService.manageFleet();

        // ByName autowired service
        FleetService fleetServiceByName = (FleetService) context.getBean("fleetServiceByName");
        fleetServiceByName.manageFleet();
    }
}
