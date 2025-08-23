package com.glo.service;

import com.glo.model.Fleet;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreightServiceImpl implements FreightService {

    private final List<Fleet> fleets = new ArrayList<>();

    @Override
    public List<Fleet> viewAllFleets() { return fleets; }

    @Override
    public Fleet viewFleetById(int fleetId) {
        return fleets.stream().filter(f -> f.getFleetId() == fleetId).findFirst().orElse(null);
    }

    @Override
    public void addFleet(Fleet fleet) { fleets.add(fleet); }

    @Override
    public void updateFleet(Fleet fleet) {
        fleets.replaceAll(f -> f.getFleetId() == fleet.getFleetId() ? fleet : f);
    }

    @Override
    public void deleteFleet(int fleetId) {
        fleets.removeIf(f -> f.getFleetId() == fleetId);
    }
}
