package com.example.RestTask.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestMeter {
    @Autowired
    private MeterRegistry meterRegistry;

    @Bean
    public Counter counter(MeterRegistry meterRegistry){
        return Counter.builder("TASK COUNTER ")
                .description("COUNTER TO REST")
                .register(meterRegistry);
    }
}
