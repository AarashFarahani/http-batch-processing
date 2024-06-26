package com.batch.processor.config;

import com.batch.api.dto.CustomerBatch;
import com.batch.api.dto.CustomerDto;
import com.batch.processor.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HttpCustomerItemReader implements ItemReader<CustomerBatch> {
    private final CustomerService customerService;
    private final String batchId = UUID.randomUUID().toString();

    @Override
    public CustomerBatch read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        var customers = this.customerService.pullCustomer();
        return new CustomerBatch(batchId, customers);
    }
}
