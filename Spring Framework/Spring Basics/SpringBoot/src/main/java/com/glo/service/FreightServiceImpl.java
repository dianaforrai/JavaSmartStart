package com.glo.service;

import com.glo.model.Fleet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreightServiceImpl implements FreightService {

    private final List<Fleet> fleets = new ArrayList<>();

    @Override
    public List<Fleet> getAllFleets() {
        return fleets;
    }

    @Override
    public Fleet getFleetById(String fleetId) {
        return fleets.stream()
                .filter(f -> f.getFleetId().equals(fleetId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addFleet(Fleet fleet) {
        fleets.add(fleet);
    }

    @Override
    public void updateFleet(Fleet fleet) {
        Fleet existing = getFleetById(fleet.getFleetId());
        if (existing != null) {
            existing.setOrigin(fleet.getOrigin());
            existing.setDestination(fleet.getDestination());
            existing.setNoOfFleets(fleet.getNoOfFleets());
            existing.setWeight(fleet.getWeight());
        }
    }

    @Override
    public void deleteFleet(String fleetId) {
        fleets.removeIf(f -> f.getFleetId().equals(fleetId));
    }
}
