package com.example.bankapp.service.Impl;

import com.example.bankapp.service.AuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    @Override
    public void log(String message) {
        System.out.println("Audit Log: " + message);
    }
}
