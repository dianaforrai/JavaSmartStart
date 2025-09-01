package com.hitachi.mobile.service;

import com.hitachi.mobile.entity.*;
import com.hitachi.mobile.repository.*;
import com.hitachi.mobile.dto.*;
import com.hitachi.mobile.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerIdentityRepository customerIdentityRepository;

    @Autowired
    private SimDetailsRepository simDetailsRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    public Customer getCustomerByUniqueId(String uniqueIdNumber) {
        return customerRepository.findByUniqueIdNumber(uniqueIdNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with unique ID: " + uniqueIdNumber));
    }

    public Customer saveCustomer(Customer customer) {
        if (customerRepository.existsByUniqueIdNumber(customer.getUniqueIdNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with unique ID: " + customer.getUniqueIdNumber());
        }
        if (customerRepository.existsByEmailAddress(customer.getEmailAddress())) {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + customer.getEmailAddress());
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmailAddress(customerDetails.getEmailAddress());
        customer.setDateOfBirth(customerDetails.getDateOfBirth());
        customer.setState(customerDetails.setState());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    public boolean validateCustomerIdentity(String uniqueIdNumber, String firstName, String lastName, String email) {
        Optional<CustomerIdentity> identity = customerIdentityRepository.findByUniqueIdNumber(uniqueIdNumber);

        if (identity.isPresent()) {
            CustomerIdentity ci = identity.get();
            return ci.getFirstName().equalsIgnoreCase(firstName) &&
                    ci.getLastName().equalsIgnoreCase(lastName) &&
                    ci.getEmailAddress().equalsIgnoreCase(email);
        }
        return false;
    }

    public SimDetails validateAndGetSimDetails(String simNumber, String serviceNumber) {
        Optional<SimDetails> simDetails = simDetailsRepository.findBySimNumber(simNumber);

        if (simDetails.isPresent() && simDetails.get().getServiceNumber().equals(serviceNumber)) {
            return simDetails.get();
        }

        throw new InvalidSimDetailsException("Invalid SIM number or service number");
    }

    public List<SimOffers> getAvailableOffers(String simNumber) {
        SimDetails simDetails = simDetailsRepository.findBySimNumber(simNumber)
                .orElseThrow(() -> new InvalidSimDetailsException("SIM not found: " + simNumber));
        return (List<SimOffers>) Optional.ofNullable(simDetails.getSimOffers())
                .orElse(List.of()) // Use an empty list if null
                .getClass()
                .cast();


    }
}
