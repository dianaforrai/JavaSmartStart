package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(1L, "John Doe", "1234567890");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer saved = customerService.createCustomer(customer);
        assertNotNull(saved);
        assertEquals("John Doe", saved.getCustName());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer(1L, "Jane Doe", "0987654321");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.getCustomerById(1L);
        assertNotNull(found);
        assertEquals("Jane Doe", found.getCustName());

        verify(customerRepository, times(1)).findById(1L);
    }
}
