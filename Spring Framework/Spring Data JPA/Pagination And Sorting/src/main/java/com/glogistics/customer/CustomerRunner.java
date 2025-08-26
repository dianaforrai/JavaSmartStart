package com.glogistics.customer;

import com.glogistics.customer.entity.Customer;
import com.glogistics.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerRunner implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        // Create sample customers
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        customerService.saveCustomer(c1);
        customerService.saveCustomer(c2);

        // Fetch with pagination and sorting
        customerService.getCustomers(0, 2, "custName").forEach(System.out::println);

        // Fetch customers with gmail
        customerService.getCustomersWithGmail().forEach(System.out::println);
    }
}
