package com.glogistics.customer.service;

import com.glogistics.customer.entity.Customer;
import com.glogistics.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Register Customer with Validation + Transaction
    @Transactional
    public Customer registerCustomer(Customer customer) {
        if (!customer.getCustEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid Email! Must contain '@'");
        }

        if (customer.getCustAddress() == null ||
                !customer.getCustAddress().matches("Bangalore|Mysore|Mangalore|Chennai|Hyderabad|Mumbai|Pune|Noida")) {
            throw new IllegalArgumentException("Invalid Address! Allowed cities only.");
        }

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

    // Delete Customer
    @Transactional
    public void deleteCustomer(Long custId) {
        customerRepository.deleteById(custId);
    }
}
