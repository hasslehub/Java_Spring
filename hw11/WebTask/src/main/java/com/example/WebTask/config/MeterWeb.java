package com.example.WebTask.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterWeb {
    @Autowired
    private MeterRegistry meterRegistry;

    /**
     * Таймер для замера пользовательских запросов к сайту
     * @param meterRegistry
     * @return
     */
    @Bean
    public Timer timer(MeterRegistry meterRegistry) {
        return Timer.builder("TIMER_USER_ACTION")
                .description("TIMER USER REQUEST")
                .register(meterRegistry);
    }
}
