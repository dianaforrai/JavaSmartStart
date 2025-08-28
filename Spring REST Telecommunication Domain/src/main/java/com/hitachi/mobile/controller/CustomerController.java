package com.hitachi.mobile.controller;

import com.hitachi.mobile.entity.*;
import com.hitachi.mobile.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // SIM number validation endpoint
    @PostMapping("/validate-sim")
    public ResponseEntity<Map<String, String>> validateSIM(
            @RequestBody @Valid Map<String, String> request) {
        String simNumber = request.get("simNumber");
        String serviceNumber = request.get("serviceNumber");

        String result = customerService.validateSIM(simNumber, serviceNumber);

        return ResponseEntity.ok(Map.of("message", result));
    }

    // Show special offers
    @GetMapping("/offers/{simId}")
    public ResponseEntity<List<SimOffers>> getOffers(@PathVariable Long simId) {
        List<SimOffers> offers = customerService.getOffers(simId);
        return ResponseEntity.ok(offers);
    }

    // Customer personal details validation
    @PostMapping("/validate-details")
    public ResponseEntity<Map<String, String>> validateCustomerDetails(
            @RequestBody @Valid Map<String, String> request) {
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String email = request.get("email");

        String result = customerService.validateCustomerDetails(firstName, lastName, email);

        return ResponseEntity.ok(Map.of("message", result));
    }

    // Update customer address
    @PutMapping("/address/{addressId}")
    public ResponseEntity<Map<String, String>> updateAddress(
            @PathVariable Long addressId,
            @RequestBody @Valid CustomerAddress address) {

        String result = customerService.updateCustomerAddress(addressId, address);

        return ResponseEntity.ok(Map.of("message", result));
    }

    // Customer ID proof validation
    @PostMapping("/validate-id-proof")
    public ResponseEntity<Map<String, String>> validateIdProof(
            @RequestBody @Valid Map<String, String> request) {
        String uniqueIdNumber = request.get("uniqueIdNumber");
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String dateOfBirth = request.get("dateOfBirth");

        String result = customerService.validateCustomerIdProof(
                uniqueIdNumber, firstName, lastName, dateOfBirth);

        return ResponseEntity.ok(Map.of("message", result));
    }

    // Customer basic details validation
    @PostMapping("/validate-basic-details")
    public ResponseEntity<Map<String, String>> validateBasicDetails(
            @RequestBody @Valid Map<String, String> request) {
        String email = request.get("email");
        String dateOfBirth = request.get("dateOfBirth");

        String result = customerService.validateCustomerBasicDetails(email, dateOfBirth);

        return ResponseEntity.ok(Map.of("message", result));
    }
}
