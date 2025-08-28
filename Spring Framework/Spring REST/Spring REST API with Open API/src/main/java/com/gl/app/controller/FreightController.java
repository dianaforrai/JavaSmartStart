package com.gl.app.controller;

import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.FreightDto;
import com.gl.app.service.CustomerService;
import com.gl.app.service.FreightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.gl.app.payload.DeleteResponse;
@RestController
@RequestMapping("/api/customers")
public class FreightController {

    private final FreightService freightService;
    private final CustomerService customerService;

    public FreightController(FreightService freightService, CustomerService customerService) {
        this.freightService = freightService;
        this.customerService = customerService;
    }

    // spring boot rest api for creating the freight
    @PostMapping("/{id}/freights")
    public ResponseEntity<FreightDto> createFreight(
            @PathVariable("id") long customerId,
            @Valid @RequestBody FreightDto freightDto
    ){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(freightService.createFreight(customerDto, freightDto),HttpStatus.CREATED);
    }

    // spring boot rest api for getting collection of freights using customer id
    @GetMapping("/{id}/freights")
    public ResponseEntity<List<FreightDto>> getFreightsByCustomerId(
            @PathVariable("id") long customerId
    ){
        return ResponseEntity.ok(freightService.getFreightByCustomerId(customerId));
    }

    // spring boot rest api for get freights using order id
    @GetMapping("/{id}/freights/{orderId}")
    public ResponseEntity<FreightDto> getFreightByOrderId(
            @PathVariable(value = "orderId") long orderId,
            @PathVariable(value = "id") long customerId
    ){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(freightService.getFreightById(customerDto,orderId));
    }

    // spring boot rest api for updating the freights
    @PutMapping("/{id}/freights/{orderId}")
    public ResponseEntity<FreightDto> updateFreightByOrderId(
            @PathVariable(value = "id") long customerId,
            @PathVariable(value = "orderId") long orderId,
            @Valid @RequestBody FreightDto freightDto
    ){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(freightService.updateFreightById(customerDto,orderId,freightDto));
    }

    // spring rest api for deleting hte freight
    @DeleteMapping("{id}/freights/{orderId}")
    public ResponseEntity<DeleteResponse> removeFreight(
            @PathVariable(value = "id") long customerId,
            @PathVariable(value = "orderId") long orderId)
    {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        freightService.removeFreight(customerDto, orderId);
        DeleteResponse deleteResponse = new DeleteResponse("Customer removed successfully!");
        return ResponseEntity.ok(deleteResponse);
    }
}
