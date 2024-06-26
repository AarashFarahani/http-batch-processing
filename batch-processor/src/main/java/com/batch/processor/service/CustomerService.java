package com.batch.processor.service;

import com.batch.api.dto.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    @Value("${pull-api.host}")
    private String uri;

    public Customer[] pullCustomer() {
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(this.uri, Customer[].class);
    }
}
