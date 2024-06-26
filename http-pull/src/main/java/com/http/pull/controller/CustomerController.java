package com.http.pull.controller;

import com.batch.api.dto.Customer;
import com.http.pull.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAllCustomers() throws IOException {
        log.info("*******************************************");
        log.info("------    /customers      -------------");
        var customers = this.customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }
}
