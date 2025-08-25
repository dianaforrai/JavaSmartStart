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

    // Register customer with validation
    public Customer registerCustomer(Customer customer) {
        if (isValid(customer)) {
            return customerRepository.save(customer);
        } else {
            System.out.println("Invalid customer details. Registration rejected.");
            return null;
        }
    }

    // Validation logic
    private boolean isValid(Customer customer) {
        return customer.getCustName() != null && !customer.getCustName().isEmpty()
                && customer.getCustEmail() != null && customer.getCustEmail().contains("@")
                && customer.getCustContactNo() != null && customer.getCustContactNo().matches("\\d{10}");
    }

    // Retrieve customer by ID
    public Optional<Customer> getCustomerById(Long custId) {
        return customerRepository.findById(custId);
    }

    // Retrieve customers by city
    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCustCity(city);
    }

    // Delete customer by ID
    public void deleteCustomer(Long custId) {
        customerRepository.deleteById(custId);
    }
}
