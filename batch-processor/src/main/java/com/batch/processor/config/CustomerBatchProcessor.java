package com.batch.processor.config;

import com.batch.api.dto.CustomerBatch;
import com.batch.api.dto.CustomerBatchWrapper;
import com.batch.api.dto.CustomerDto;
import com.batch.api.dto.CustomerWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class CustomerBatchProcessor implements ItemProcessor<CustomerBatch, CustomerBatchWrapper> {
    @Override
    public CustomerBatchWrapper process(CustomerBatch customerBatch) throws Exception {
        var id = UUID.randomUUID().toString();
        log.info("{} | {}", id, customerBatch);
        return new CustomerBatchWrapper(id, customerBatch.getBatchId(), customerBatch.getCustomers());
    }
}
