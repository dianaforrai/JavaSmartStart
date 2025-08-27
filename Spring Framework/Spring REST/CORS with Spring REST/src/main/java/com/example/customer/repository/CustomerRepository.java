package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private Map<Long, Customer> customerStore = new HashMap<>();
    private Long idCounter = 1L;

    public Customer save(Customer customer) {
        customer.setId(idCounter++);
        customerStore.put(customer.getId(), customer);
        return customer;
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerStore.get(id));
    }
}
