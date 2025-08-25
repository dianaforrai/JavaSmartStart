package com.glo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create or update a customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long custId) {
        return customerRepository.findById(custId);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Delete customer
    public void deleteCustomer(Long custId) {
        if(!customerRepository.existsById(custId)) {
            throw new IllegalArgumentException("Customer with ID " + custId + " does not exist.");
        }
        customerRepository.deleteById(custId);
    }

    // Find customers by city with validation
    public List<Customer> getCustomersByCity(String custCity) {
        if(!StringUtils.hasText(custCity)) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        return customerRepository.findByCustCity(custCity);
    }
}
