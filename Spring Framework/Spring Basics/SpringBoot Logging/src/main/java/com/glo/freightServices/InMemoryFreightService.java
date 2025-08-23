package com.glo.freightServices;

import com.glo.freight.model.Fleet;
import com.glo.freight.service.FreightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryFreightService implements FreightService {

    private static final Logger log = LogManager.getLogger(InMemoryFreightService.class);

    private final Map<String, Fleet> store = new LinkedHashMap<>();

    public InMemoryFreightService() {
        // Seed a couple of records
        addFleet(new Fleet("F-100", "NYC", "LA", "5"));
        addFleet(new Fleet("F-200", "Paris", "Berlin", "2"));
    }

    @Override
    public List<Fleet> viewAllFleets() {
        log.info("Fetching all fleets");
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Fleet> viewFleetById(String fleetId) {
        log.info("Fetching fleet by id: {}", fleetId);
        return Optional.ofNullable(store.get(fleetId));
    }

    @Override
    public void addFleet(Fleet fleet) {
        log.info("Adding fleet: {}", fleet);
        store.put(fleet.getFleetId(), fleet);
    }

    @Override
    public boolean updateFleet(Fleet fleet) {
        if (!store.containsKey(fleet.getFleetId())) {
            log.warn("Attempt to update non-existing fleet: {}", fleet.getFleetId());
            return false;
        }
        log.info("Updating fleet: {}", fleet);
        store.put(fleet.getFleetId(), fleet);
        return true;
    }

    @Override
    public boolean deleteFleet(String fleetId) {
        log.info("Deleting fleet: {}", fleetId);
        return store.remove(fleetId) != null;
    }
}
