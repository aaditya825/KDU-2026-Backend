package com.logisticshub.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AsyncConfig {

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService bookScheduler()
    {
        return Executors.newScheduledThreadPool(1, Thread::new);
    }
}
