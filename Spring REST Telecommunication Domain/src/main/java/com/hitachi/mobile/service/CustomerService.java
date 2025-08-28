package com.hitachi.mobile.service;

import com.hitachi.mobile.entity.*;
import com.hitachi.mobile.exception.*;
import com.hitachi.mobile.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private SimDetailsRepository simDetailsRepository;

    @Autowired
    private SimOffersRepository simOffersRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerIdentityRepository customerIdentityRepository;

    // SIM Details validation and activation
    public String validateSIM(String simNumber, String serviceNumber) {
        // Check if SIM exists and validate service number
        Optional<SimDetails> simOpt = simDetailsRepository.findByServiceNumber(serviceNumber);

        if (simOpt.isEmpty()) {
            throw new InvalidDetailsException("Invalid SIM/Service details, if the provided SIM/Service number is invalid, throw InvalidDetailsException custom exception(already defined) and display \"Invalid details, please check again Subscriber Identity Module (SIM)status.\" error message, with HttpStatus.NOT_FOUND");
        }

        SimDetails sim = simOpt.get();

        if (!sim.getSimNumber().equals(simNumber)) {
            throw new InvalidDetailsException("Invalid details, please check again Subscriber Identity Module (SIM)status.");
        }

        if ("active".equals(sim.getSimStatus())) {
            throw new InvalidDetailsException("Subscriber Identity Module (SIM)already active");
        }

        // Activate SIM
        sim.setSimStatus("active");
        simDetailsRepository.save(sim);

        return "SIM activated successfully";
    }

    // Get available offers for SIM
    public List<SimOffers> getOffers(Long simId) {
        return simOffersRepository.findBySimId(simId);
    }

    // Validate customer personal details
    public String validateCustomerDetails(String firstName, String lastName, String email) {
        // Validate first name and last name with existing details
        Optional<CustomerIdentity> customerOpt = customerIdentityRepository.findByEmailAddress(email);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("No customer found for the provided details");
        }

        CustomerIdentity customer = customerOpt.get();

        if (!customer.getFirstName().equals(firstName) || !customer.getLastName().equals(lastName)) {
            throw new InvalidDetailsException("Invalid email details");
        }

        return "Customer details validated successfully";
    }

    // Update customer address
    public String updateCustomerAddress(Long addressId, CustomerAddress newAddress) {
        Optional<CustomerAddress> existingAddressOpt = customerAddressRepository.findById(addressId);

        if (existingAddressOpt.isEmpty()) {
            // Add new address if not exists
            customerAddressRepository.save(newAddress);
            return "Address added successfully";
        } else {
            // Update existing address
            CustomerAddress existingAddress = existingAddressOpt.get();
            existingAddress.setAddress(newAddress.getAddress());
            existingAddress.setCity(newAddress.getCity());
            existingAddress.setState(newAddress.getState());
            existingAddress.setPinCode(newAddress.getPinCode());
            customerAddressRepository.save(existingAddress);
            return "Address updated successfully";
        }
    }

    // Customer ID proof validation
    public String validateCustomerIdProof(String uniqueIdNumber, String firstName,
                                          String lastName, String dateOfBirth) {
        Optional<CustomerIdentity> customerOpt = customerIdentityRepository.findById(uniqueIdNumber);

        if (customerOpt.isEmpty()) {
            throw new DetailsDoesNotExistException("No request placed for you");
        }

        CustomerIdentity customer = customerOpt.get();

        // Validate all details match
        if (!customer.getFirstName().equals(firstName) ||
                !customer.getLastName().equals(lastName) ||
                !customer.getDateOfBirth().toString().equals(dateOfBirth)) {
            throw new InvalidDetailsException("Invalid details");
        }

        return "Customer ID proof validated successfully";
    }

    // Validate customer basic details against stored data
    public String validateCustomerBasicDetails(String email, String dateOfBirth) {
        Optional<CustomerIdentity> customerOpt = customerIdentityRepository.findByEmailAddress(email);

        if (customerOpt.isEmpty()) {
            throw new DetailsDoesNotExistException("No request placed for you");
        }

        CustomerIdentity customer = customerOpt.get();

        if (!customer.getDateOfBirth().toString().equals(dateOfBirth)) {
            throw new InvalidDetailsException("Invalid email details");
        }

        return "Customer basic details validated successfully";
    }
}