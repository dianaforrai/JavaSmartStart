package com.globallogic.booking.client;

import com.globallogic.booking.dto.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight-service")
public interface FlightServiceClient {

    @GetMapping("/flights/{flightId}")
    Flight getFlightById(@PathVariable("flightId") Long flightId);
}