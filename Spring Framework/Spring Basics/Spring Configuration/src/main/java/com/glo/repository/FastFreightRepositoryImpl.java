package com.glo.repository;

import com.glo.model.Fleet;
import java.util.ArrayList;
import java.util.List;

public class FastFreightRepositoryImpl implements FastFreightRepository {

    private final List<Fleet> fleets = new ArrayList<>();

    public FastFreightRepositoryImpl() {
        fleets.add(new Fleet(1, "New York", "Los Angeles", 5));
        fleets.add(new Fleet(2, "Chicago", "Houston", 3));
    }

    @Override
    public List<Fleet> getAllFleets() {
        return fleets;
    }

    @Override
    public Fleet getFleetById(long fleetId) {
        return fleets.stream()
                .filter(f -> f.getFleetId() == fleetId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveFleet(Fleet fleet) {
        fleets.add(fleet);
    }

    @Override
    public void updateFleet(Fleet fleet) {
        Fleet existing = getFleetById(fleet.getFleetId());
        if (existing != null) {
            existing.setOrigin(fleet.getOrigin());
            existing.setDestination(fleet.getDestination());
            existing.setRequiredFleets(fleet.getRequiredFleets());
        }
    }

    @Override
    public boolean deleteFleet(long fleetId) {
        return fleets.removeIf(f -> f.getFleetId() == fleetId);
    }
}
