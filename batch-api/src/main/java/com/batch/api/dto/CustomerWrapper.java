package com.batch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CustomerWrapper implements Serializable {
    private String id;
    private String jobId;
    private Customer customer;
}
