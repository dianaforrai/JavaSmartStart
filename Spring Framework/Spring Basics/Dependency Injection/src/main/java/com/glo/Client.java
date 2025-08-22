package com.glo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        // Constructor Injection for Fleet
        Fleet fleet1 = (Fleet) context.getBean("fleetConstructor");
        fleet1.displayFleetInfo();

        // Setter Injection for Fleet
        Fleet fleet2 = (Fleet) context.getBean("fleetSetter");
        fleet2.displayFleetInfo();

        // Constructor Injection for Freight
        Freight freight = (Freight) context.getBean("freight");
        freight.displayFreightInfo();
    }
}
