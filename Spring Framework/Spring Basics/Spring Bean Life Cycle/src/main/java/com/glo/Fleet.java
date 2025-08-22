package com.glo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Fleet implements InitializingBean, DisposableBean {

    private Integer fleetId;
    private Integer numberOfFleets;
    private String origin;
    private String destination;

    public Fleet() {
        System.out.println("Fleet constructor called");
    }

    // Factory method
    public static Fleet createFleetInstance() {
        System.out.println("Factory method: Creating Fleet instance");
        return new Fleet();
    }

    // Getters and setters
    public Integer getFleetId() { return fleetId; }
    public void setFleetId(Integer fleetId) { this.fleetId = fleetId; }

    public Integer getNumberOfFleets() { return numberOfFleets; }
    public void setNumberOfFleets(Integer numberOfFleets) { this.numberOfFleets = numberOfFleets; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    // Custom init method
    public void customInitMethod() {
        System.out.println("Custom init method called");
    }

    // Custom destroy method
    public void customDestroyMethod() {
        System.out.println("Custom destroy method called");
    }

    // InitializingBean callback
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() called");
    }

    // DisposableBean callback
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() called");
    }

    @PostConstruct
    public void postConstructInit() {
        System.out.println("@PostConstruct method called");
    }

    @PreDestroy
    public void preDestroyCleanup() {
        System.out.println("@PreDestroy method called");
    }
}
