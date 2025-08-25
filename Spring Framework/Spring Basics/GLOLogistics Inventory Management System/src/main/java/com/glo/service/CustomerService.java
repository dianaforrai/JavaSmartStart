package com.glo.service;

import com.glo.model.Customer;
import com.glo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        logger.info("Adding new customer: {}", customer.getCustName());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        logger.info("Updating customer with ID: {}", customer.getCustId());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        logger.info("Deleting customer with ID: {}", customerId);
        customerRepository.deleteById(customerId);
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);
        return customers;
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}