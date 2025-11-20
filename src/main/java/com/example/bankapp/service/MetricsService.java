package com.example.bankapp.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final MeterRegistry registry;

    public MetricsService(MeterRegistry registry) {
        this.registry = registry;
    }

    public void incrementAccountCreated() {
        registry.counter("bankapp.account.created").increment();
    }
}
