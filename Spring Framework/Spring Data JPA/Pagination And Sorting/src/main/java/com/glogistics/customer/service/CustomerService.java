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

    private static final List<String> VALID_CITIES = Arrays.asList(
            "Bangalore", "Mysore", "Mangalore", "Chennai",
            "Hyderabad", "Mumbai", "Pune", "Noida"
    );

    // Validate customer details
    public void validateCustomer(Customer customer) {
        if (customer.getCustEmail() == null || !customer.getCustEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (customer.getCustAddress() == null || !VALID_CITIES.contains(customer.getCustAddress())) {
            throw new IllegalArgumentException("Invalid address/city");
        }
    }

    // Save customer after validation
    public Customer saveCustomer(Customer customer) {
        try {
            validateCustomer(customer);
            return customerRepository.save(customer);
        } catch (Exception e) {
            System.err.println("Error saving customer: " + e.getMessage());
            throw e;
        }
    }

    // Fetch all customers with pagination and sorting
    public Page<Customer> getCustomers(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return customerRepository.findAll(pageable);
    }

    // Fetch customers with gmail.com emails
    public List<Customer> getCustomersWithGmail() {
        return customerRepository.findCustomersByEmailDomain("gmail.com");
    }
}
