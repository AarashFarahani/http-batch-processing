package com.batch.processor.config;

import com.batch.api.dto.Customer;
import com.batch.api.dto.CustomerDto;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class HttpItemReader implements ItemReader<CustomerDto> {
    @Value("${pull-api.host}")
    private String uri;// = "http://localhost:8180/customers";
    private LinkedBlockingQueue<CustomerDto> queue;
    private final String jobId = UUID.randomUUID().toString();

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before reading item");

        var restTemplate = new RestTemplate();
        var result = restTemplate.getForObject(this.uri, Customer[].class);
        var customerBatchList = Arrays.stream(result)
                .map(a-> new CustomerDto(this.jobId, a))
                .toList();
        this.queue = new LinkedBlockingQueue<>(customerBatchList);
        stepExecution.getExecutionContext().put(this.jobId, result);
    }

    @Override
    public CustomerDto read() throws Exception {
        Thread.sleep(1000);
        return this.queue.poll();
    }
}
