package com.example.crm.controller;

import com.example.crm.model.Customer;
import com.example.crm.service.CustomerService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/hateoas/customers")
public class CustomerHateoasController {

    private final CustomerService customerService;

    public CustomerHateoasController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {
        List<EntityModel<Customer>> customers = customerService.getAllCustomers()
                .stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerHateoasController.class).getCustomerById(customer.getCustId())).withSelfRel(),
                        linkTo(methodOn(CustomerHateoasController.class).getAllCustomers()).withRel("all-customers"),
                        linkTo(methodOn(CustomerController.class).createCustomer(null)).withRel("create"),
                        linkTo(methodOn(CustomerController.class).updateCustomer(customer.getCustId(), null)).withRel("update"),
                        linkTo(methodOn(CustomerController.class).deleteCustomer(customer.getCustId())).withRel("delete")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerHateoasController.class).getAllCustomers()).withSelfRel());
    }

    // Get customer by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerHateoasController.class).getCustomerById(id)).withSelfRel(),
                linkTo(methodOn(CustomerHateoasController.class).getAllCustomers()).withRel("all-customers"),
                linkTo(methodOn(CustomerController.class).createCustomer(null)).withRel("create"),
                linkTo(methodOn(CustomerController.class).updateCustomer(id, null)).withRel("update"),
                linkTo(methodOn(CustomerController.class).deleteCustomer(id)).withRel("delete")
        );
    }
}
