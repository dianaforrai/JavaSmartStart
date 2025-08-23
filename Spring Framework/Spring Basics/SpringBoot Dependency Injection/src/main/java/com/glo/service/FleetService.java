package com.glo.service;

import com.glo.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FleetService {

    private final List<Vehicle> fleet = new ArrayList<>();

    // Add vehicle
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
        System.out.println("Added vehicle: " + vehicle);
    }

    // Update vehicle
    public void updateVehicle(Vehicle vehicle) {
        Optional<Vehicle> existing = fleet.stream()
                .filter(v -> v.getId().equals(vehicle.getId()))
                .findFirst();

        if (existing.isPresent()) {
            fleet.remove(existing.get());
            fleet.add(vehicle);
            System.out.println("Updated vehicle: " + vehicle);
        } else {
            System.out.println("Vehicle not found: " + vehicle.getId());
        }
    }

    // Remove vehicle
    public void removeVehicle(Long vehicleId) {
        fleet.removeIf(v -> v.getId().equals(vehicleId));
        System.out.println("Removed vehicle with ID: " + vehicleId);
    }

    // List all vehicles
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(fleet);
    }
}
