package com.glo.repository;

import com.glo.model.Fleet;
import java.util.List;

public interface FastFreightRepository {
    List<Fleet> getAllFleets();
    Fleet getFleetById(long fleetId);
    void saveFleet(Fleet fleet);
    void updateFleet(Fleet fleet);
    boolean deleteFleet(long fleetId);
}
