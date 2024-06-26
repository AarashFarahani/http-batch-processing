//package com.batch.processor.config;
//
//import com.batch.api.dto.Customer;
//import com.batch.api.dto.CustomerWrapper;
//import com.batch.processor.listener.JobCompletionNotificationListener;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class SpringBatchConfig {
//    private static final String CSV_FILE_PATH = "data/customers.csv";
//
//    private final CustomerProcessor customerProcessor;
//    private final LoggingItemWriter loggingItemWriter;
//    private final JobCompletionNotificationListener jobCompletionNotificationListener;
//
//    @Bean
//    public FlatFileItemReader<Customer> reader() {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("personItemReader")
//                .resource(new ClassPathResource(CSV_FILE_PATH))
//                .linesToSkip(1)
//                .delimited()
//                .names(new String[]{"index", "customerId", "firstName", "lastName"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                    setTargetType(Customer.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("csv-step", jobRepository)
//                .<Customer, CustomerWrapper>chunk(10, platformTransactionManager)
//                .reader(reader())
//                .processor(this.customerProcessor)
//                .writer(this.loggingItemWriter)
//                .listener(this.jobCompletionNotificationListener)
//                .build();
//    }
//
//    @Bean
//    public Job customerJob(JobRepository jobRepository, Step step1) {
//        return new JobBuilder("Customer", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step1)
//                .build();
//    }
//}
