package com.batch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerBatch {
    private String batchId;
    private Customer[] customers;
}
