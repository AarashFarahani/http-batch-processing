package com.http.push.controller;

import com.batch.api.dto.CustomerWrapper;
import com.http.push.repository.CustomerRepository;
import com.http.push.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class PushController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CustomerWrapper customerWrapper) {
        var id = this.customerService.create(customerWrapper);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/")
    public ResponseEntity create() {
        var customerEntities = this.customerRepository.findAll();
        return ResponseEntity.ok(customerEntities);
    }
}
