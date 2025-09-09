package com.globallogic.booking.client;

import com.globallogic.booking.dto.Passenger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service")
public interface PassengerServiceClient {

    @GetMapping("/passengers/{passengerId}")
    Passenger getPassengerById(@PathVariable("passengerId") Long passengerId);
}