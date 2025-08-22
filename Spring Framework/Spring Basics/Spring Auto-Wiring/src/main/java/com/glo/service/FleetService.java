package com.glo.service;

import com.glo.repository.FleetRepository;

public class FleetService {
    private FleetRepository fleetRepository;

    // Constructor-based injection
    public FleetService(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;
    }

    // Default constructor for byName injection
    public FleetService() {}

    public void manageFleet() {
        System.out.println("Managing fleet...");
        fleetRepository.saveFleet();
    }

    // Setter for byName autowiring
    public void setFleetRepository(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;
    }
}
