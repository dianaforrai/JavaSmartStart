package com.example.crm.controller;

import com.example.crm.model.Customer;
import com.example.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.crm.dto.CustomerDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustName(customerDTO.getCustName());
        customer.setCustContactNo(customerDTO.getCustContactNo());
        customer.setCustEmail(customerDTO.getCustEmail());
        customer.setCustCity(customerDTO.getCustCity());
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustName(customerDTO.getCustName());
        customer.setCustContactNo(customerDTO.getCustContactNo());
        customer.setCustEmail(customerDTO.getCustEmail());
        customer.setCustCity(customerDTO.getCustCity());
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public Class<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return null;
    }

    @GetMapping("/page")
    public Page<Customer> getCustomersPaginatedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "custId") String sortBy
    ) {
        return customerService.getCustomersPaginatedAndSorted(page, size, sortBy);
    }
}