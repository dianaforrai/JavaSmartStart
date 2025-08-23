package com.glo.service;

import com.glo.model.Fleet;
import java.util.List;

public interface FreightService {
    List<Fleet> viewAllFleets();
    Fleet viewFleetById(int fleetId);
    void addFleet(Fleet fleet);
    void updateFleet(Fleet fleet);
    void deleteFleet(int fleetId);
}
