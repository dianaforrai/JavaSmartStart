package com.glo.service;

import com.glo.model.Fleet;
import java.util.List;

public interface FreightService {
    List<Fleet> getAllFleets();
    Fleet getFleetById(String fleetId);
    void addFleet(Fleet fleet);
    void updateFleet(Fleet fleet);
    void deleteFleet(String fleetId);
}
