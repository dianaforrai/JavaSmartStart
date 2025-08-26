package com.glogistics.customer.service;

import com.glogistics.customer.entity.Customer;
import com.glogistics.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final List<String> allowedCities = Arrays.asList(
            "Bangalore", "Mysore", "Mangalore", "Chennai",
            "Hyderabad", "Mumbai", "Pune", "Noida"
    );

    // Validate customer details
    public void validateCustomer(Customer customer) throws Exception {
        if (!customer.getCustEmail().contains("@")) {
            throw new Exception("Invalid email format");
        }
        if (!allowedCities.contains(customer.getCustCity())) {
            throw new Exception("City not allowed");
        }
        if (customer.getCustAddress() == null || customer.getCustAddress().isEmpty()) {
            throw new Exception("Address is mandatory");
        }
    }

    // Save customer with validation
    public Customer saveCustomer(Customer customer) throws Exception {
        try {
            validateCustomer(customer);
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new Exception("Customer validation failed: " + e.getMessage());
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // Pagination + Sorting + Optional Defaults
    public Page<Customer> getCustomers(
            Integer pageNumber,
            Integer pageSize,
            String sortField,
            String sortDir
    ) {
        int page = (pageNumber == null || pageNumber < 0) ? 0 : pageNumber;
        int size = (pageSize == null || pageSize <= 0) ? 5 : pageSize;
        String field = (sortField == null || sortField.isEmpty()) ? "custName" : sortField;
        Sort.Direction direction = (sortDir == null || sortDir.equalsIgnoreCase("asc"))
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, field));
        return customerRepository.findAll(pageable);
    }

    // Fetch customers with gmail.com emails
    public List<Customer> getCustomersWithGmail() {
        return customerRepository.findByCustEmailEndingWith("gmail.com");
    }
}
