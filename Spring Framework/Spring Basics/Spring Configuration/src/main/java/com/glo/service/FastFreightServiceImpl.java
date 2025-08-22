package com.glo.service;

import com.glo.model.Fleet;
import com.glo.repository.FastFreightRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastFreightServiceImpl implements FastFreightService {

    private final FastFreightRepository repository;

    @Autowired
    public FastFreightServiceImpl(FastFreightRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Fleet> getAllFleets() {
        return repository.getAllFleets();
    }

    @Override
    public Fleet getFleetById(long fleetId) {
        return repository.getFleetById(fleetId);
    }

    @Override
    public void saveFleet(Fleet fleet) {
        repository.saveFleet(fleet);
    }

    @Override
    public void updateFleet(Fleet fleet) {
        repository.updateFleet(fleet);
    }

    @Override
    public boolean deleteFleet(long fleetId) {
        return repository.deleteFleet(fleetId);
    }
}
