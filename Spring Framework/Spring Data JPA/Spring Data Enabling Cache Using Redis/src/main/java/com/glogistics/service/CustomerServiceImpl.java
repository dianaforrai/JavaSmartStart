package com.glogistics.service;

import com.glogistics.entity.Customer;
import com.glogistics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Cacheable(value = "customers", key = "#custId")
    public Customer getCustomer(Long custId) {
        System.out.println("Fetching from DB..."); // to show caching effect
        return customerRepository.findById(custId).orElse(null);
    }
}
