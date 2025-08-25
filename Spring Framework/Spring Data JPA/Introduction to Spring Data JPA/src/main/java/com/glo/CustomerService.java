package com.glo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create or Update Customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Retrieve customer by ID
    public Optional<Customer> getCustomerById(Long custId) {
        return customerRepository.findById(custId);
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Delete customer
    public void deleteCustomer(Long custId) {
        customerRepository.deleteById(custId);
    }
}
