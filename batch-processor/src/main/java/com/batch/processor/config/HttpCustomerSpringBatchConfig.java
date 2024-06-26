//package com.batch.processor.config;
//
//import com.batch.api.dto.CustomerBatch;
//import com.batch.api.dto.CustomerBatchWrapper;
//import com.batch.api.dto.CustomerDto;
//import com.batch.api.dto.CustomerWrapper;
//import com.batch.processor.listener.JobCompletionNotificationListener;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class HttpCustomerSpringBatchConfig {
//    private final CustomerBatchProcessor customerProcessor;
//    private final JobCompletionNotificationListener jobCompletionNotificationListener;
//    private final HttpCustomerItemReader httpItemReader;
//    private final HttpCustomerItemWriter httpItemWriter;
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("http-step", jobRepository)
//                .<CustomerBatch, CustomerBatchWrapper>chunk(2, platformTransactionManager)
//                .reader(this.httpItemReader)
//                .processor(this.customerProcessor)
//                .writer(this.httpItemWriter)
//                .listener(this.jobCompletionNotificationListener)
//                .allowStartIfComplete(true)
////                .taskExecutor(this.taskExecutor())//Multi thread
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
