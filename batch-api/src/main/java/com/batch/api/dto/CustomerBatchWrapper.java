package com.batch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerBatchWrapper {
    private String id;
    private String batchId;
    private Customer[] customers;
}
