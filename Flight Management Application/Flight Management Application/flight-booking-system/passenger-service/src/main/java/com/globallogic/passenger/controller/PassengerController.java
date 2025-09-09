package com.globallogic.passenger.controller;

import com.globallogic.passenger.entity.Passenger;
import com.globallogic.passenger.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long passengerId) {
        return passengerService.getPassengerById(passengerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Passenger createPassenger(@Valid @RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PutMapping("/{passengerId}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long passengerId,
                                                     @Valid @RequestBody Passenger passenger) {
        return passengerService.getPassengerById(passengerId)
                .map(existingPassenger -> {
                    passenger.setPassengerId(passengerId);
                    return ResponseEntity.ok(passengerService.savePassenger(passenger));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        if (passengerService.getPassengerById(passengerId).isPresent()) {
            passengerService.deletePassenger(passengerId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}