package com.globallogic.flight.controller;

import com.globallogic.flight.entity.Flight;
import com.globallogic.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        return flightService.getFlightById(flightId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flight createFlight(@Valid @RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId,
                                               @Valid @RequestBody Flight flight) {
        return flightService.getFlightById(flightId)
                .map(existingFlight -> {
                    flight.setFlightId(flightId);
                    return ResponseEntity.ok(flightService.saveFlight(flight));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        if (flightService.getFlightById(flightId).isPresent()) {
            flightService.deleteFlight(flightId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}