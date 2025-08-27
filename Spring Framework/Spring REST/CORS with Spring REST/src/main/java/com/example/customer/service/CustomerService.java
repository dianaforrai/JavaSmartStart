package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return repository.findById(id);
    }
}
