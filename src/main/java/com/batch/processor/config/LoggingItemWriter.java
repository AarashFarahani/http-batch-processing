package com.batch.processor.config;

import com.batch.api.dto.CustomerWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LoggingItemWriter implements ItemWriter<CustomerWrapper> {
    @Override
    public void write(Chunk<? extends CustomerWrapper> chunk) throws Exception {
        log.info(chunk);
    }
}
