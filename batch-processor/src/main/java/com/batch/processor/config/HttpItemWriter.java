package com.batch.processor.config;

import com.batch.api.dto.CustomerDto;
import com.batch.api.dto.CustomerWrapper;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HttpItemWriter implements ItemWriter<CustomerWrapper> {
    @Value("${push-api.host}")
    private String uri;

    @Override
    public void write(Chunk<? extends CustomerWrapper> chunk) throws Exception {
        Observable.fromArray(chunk.getItems().toArray(CustomerWrapper[]::new))
                .subscribe(this::pushCustomer);
    }

    private void pushCustomer(CustomerWrapper customerWrapper) {
        var restTemplate = new RestTemplate();
        restTemplate.postForEntity(this.uri, customerWrapper, String.class);
    }
}
