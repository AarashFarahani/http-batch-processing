package com.batch.processor.config;

import com.batch.api.dto.Customer;
import com.batch.api.dto.CustomerDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ReactiveHttpItemReader implements ItemReader<CustomerDto> {
    @Value("${pull-api.host}")
    private String uri;// = "http://localhost:8180/customers";
    private final Object lock = new Object();
    private LinkedBlockingQueue<CustomerDto> queue;
    private final String jobId = UUID.randomUUID().toString();

    private void initQueue(Customer[] customers) {
        synchronized (this.lock) {
            if (this.queue == null) {
                var customerBatchList = Arrays.stream(customers)
                        .map(a-> new CustomerDto(this.jobId, a))
                        .toList();
                this.queue = new LinkedBlockingQueue<>(customerBatchList);
            }
        }
    }

    @Override
    public CustomerDto read() throws Exception {
        if (queue == null) {
            var restTemplate = new RestTemplate();
            var result = restTemplate.getForObject(this.uri, Customer[].class);
            this.initQueue(result);
        }

        if (this.queue.peek() != null) {
            return this.queue.poll();
        } else {
            this.queue = null;
            return null;
        }
    }
}
