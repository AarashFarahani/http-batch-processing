package com.batch.processor.config;

import com.batch.api.dto.CustomerDto;
import com.batch.api.dto.CustomerWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class CustomerProcessor implements ItemProcessor<CustomerDto, CustomerWrapper> {
    @Override
    public CustomerWrapper process(CustomerDto customerDto) throws Exception {
        var id = UUID.randomUUID().toString();
        log.info("{} | {}", id, customerDto);
        return new CustomerWrapper(id, customerDto.getJobId(), customerDto.getCustomer());
    }
}
