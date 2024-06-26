package com.http.pull.service;

import com.batch.api.dto.Customer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private static final String CSV_FILE_PATH = "data/customers.csv";

    public List<Customer> getAllCustomer() throws IOException {
        var csvFile = new ClassPathResource(CSV_FILE_PATH).getFile();
        var customers = new ArrayList<Customer>();

        try (var streamLines = Files.lines(Paths.get(csvFile.getPath()), Charset.defaultCharset())) {
            var lines = streamLines.toList();

            for (int i = 1; i < lines.size(); i++) {
                var line = lines.get(i);
                var splitted = line.split(",");
                var customer = new Customer(Integer.valueOf(splitted[0]), String.valueOf(splitted[1]),
                        String.valueOf(splitted[2]), String.valueOf(splitted[3]));
                customers.add(customer);
            }
        }

        return customers;
    }
}
