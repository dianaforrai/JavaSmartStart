package com.gl.app.service;

import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.FreightDto;
import java.util.List;

public interface FreightService {
    FreightDto createFreight(CustomerDto customerDto, FreightDto freightDto);
    List<FreightDto> getFreightByCustomerId(long customerId);
    FreightDto getFreightById(CustomerDto customerDto,long orderId);
    FreightDto updateFreightById(CustomerDto customerDto, long orderId, FreightDto freightDto);
    void removeFreight(CustomerDto customerDto, long orderId);
}