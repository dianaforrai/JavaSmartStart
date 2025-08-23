package com.glo.freight.service;

import com.glo.freight.model.Fleet;

import java.util.List;
import java.util.Optional;

public interface FreightService {
    List<Fleet> viewAllFleets();
    Optional<Fleet> viewFleetById(String fleetId);
    void addFleet(Fleet fleet);
    boolean updateFleet(Fleet fleet);
    boolean deleteFleet(String fleetId);
}
