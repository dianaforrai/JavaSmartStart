package com.glo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // Load Spring config
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // Get beans
        Fleet fleet = (Fleet) context.getBean("fleet");
        Client client = (Client) context.getBean("client");

        // Print details
        System.out.println(fleet);
        System.out.println(client);
    }
}
