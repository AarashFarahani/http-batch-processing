package com.batch.processor.config;

import com.batch.api.dto.CustomerBatchWrapper;
import com.batch.api.dto.CustomerWrapper;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpCustomerItemWriter implements ItemWriter<CustomerBatchWrapper> {
    @Value("${push-api.host}")
    private String uri;

    @Override
    public void write(Chunk<? extends CustomerBatchWrapper> chunk) throws Exception {
        var customerBatchWrappers = chunk.getItems();
        var restTemplate = new RestTemplate();

        for (var customerBatchWrapper : customerBatchWrappers) {
            for (var customer : customerBatchWrapper.getCustomers()) {
                var customerWrapper = new CustomerWrapper(customerBatchWrapper.getId(), customerBatchWrapper.getBatchId(), customer);
                restTemplate.postForEntity(this.uri, customerWrapper, String.class);
            }
        }
    }
}
