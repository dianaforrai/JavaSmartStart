package com.example.crm.service;

import com.example.crm.exception.CustomerNotFoundException;
import com.example.crm.model.Customer;
import com.example.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public List<Customer> getAllCustomers() // v1
    {
        return null;
    }

    public List<Customer> getAllCustomers(Pageable pageable) // v2
    {
        return null;
    }

    public Customer getCustomerById(Long id) {
        return null;
    }
    public Customer saveCustomer(Customer customer) {
        return null;
    }
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }
    public void deleteCustomer(Long id) {
    }

    public Page<Customer> getCustomersPaginatedAndSorted(int page, int size, String sortBy) {
        return null;
    }
    public Page<Customer> getCustomers(Integer pageNumber, Integer pageSize, String sortField, String sortDir) {
        return null;
    }
    public List<Customer> getCustomersWithGmail() {
        return null;
    }

}

