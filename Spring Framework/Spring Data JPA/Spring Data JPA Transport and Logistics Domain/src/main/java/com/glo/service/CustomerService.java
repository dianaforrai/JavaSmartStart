package com.glo.service;

import com.glo.model.Customer;
import com.glo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Add new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer details
    public Customer updateCustomer(Customer customer) {
        if (customerRepository.existsById(customer.getCustId())) {
            return customerRepository.save(customer);
        }
        throw new RuntimeException("Customer not found with id: " + customer.getCustId());
    }

    // Delete customer
    public void deleteCustomer(Integer customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }

    // View all customers
    public List<Customer> viewAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
}