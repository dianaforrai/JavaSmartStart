package com.glo.client;

import com.glo.service.FastFreightService;
import org.springframework.stereotype.Component;

public class Client {

    private double weight;

    private final FastFreightService freightService;

    // Constructor injection for weight
    public Client(FastFreightService freightService) {
        this.freightService = freightService;
        this.weight = 1000.0; // default value
    }

    // Getter & Setter for weight
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public void showWeight() {
        System.out.println("Fleet weight: " + weight);
    }
}
