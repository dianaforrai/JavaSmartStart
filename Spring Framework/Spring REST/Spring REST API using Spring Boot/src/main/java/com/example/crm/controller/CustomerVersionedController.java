package com.example.crm.controller;

import com.example.crm.model.Customer;
import com.example.crm.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerVersionedController {

    private final CustomerService customerService;

    public CustomerVersionedController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Version 1 - normal GET all customers
    @GetMapping("/v1/customers")
    public List<Customer> getAllCustomersV1() {
        return customerService.getAllCustomers();
    }

    // Version 2 - GET with pagination and sorting
    @GetMapping("/v2/customers")
    public List<Customer> getAllCustomersV2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "custName") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return customerService.getAllCustomers(pageable);
    }
}
