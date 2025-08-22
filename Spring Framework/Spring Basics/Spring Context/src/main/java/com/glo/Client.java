package com.glo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        // Load context from classpath XML
        ApplicationContext classPathContext =
                new ClassPathXmlApplicationContext("classpath-config.xml");
        Greeting greetingFromClasspath = (Greeting) classPathContext.getBean("greeting");
        System.out.println("ClassPathXmlApplicationContext: " + greetingFromClasspath.getMessage());

        // Load context from filesystem XML
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath-config.xml", "filesystem-config.xml");

        Greeting greeting1 = (Greeting) context.getBean("greeting");
        System.out.println("FileSystemXmlApplicationContext: " + greetingFromClasspath.getMessage());
    }
}