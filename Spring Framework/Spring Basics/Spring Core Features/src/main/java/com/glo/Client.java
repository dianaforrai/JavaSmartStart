package com.glo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // Get Fleet bean
        Fleet fleet = (Fleet) context.getBean("fleet");

        // Print default weight
        System.out.println("Fleet weight: " + fleet.getWeight());
    }
}
