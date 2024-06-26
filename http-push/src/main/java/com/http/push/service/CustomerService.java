package com.http.push.service;

import com.batch.api.dto.CustomerWrapper;
import com.http.push.entity.CustomerEntity;
import com.http.push.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public String create(CustomerWrapper customerWrapper) {
        var id = UUID.randomUUID().toString();
        var customer = customerWrapper.getCustomer();
        var customerEntity = new CustomerEntity(id, customerWrapper.getJobId(), customerWrapper.getId(), customer.getIndex(), customer.getFirstName(), customer.getLastName());
        log.info(customerEntity);
        this.customerRepository.save(customerEntity);
        return customerEntity.getId();
    }
}
