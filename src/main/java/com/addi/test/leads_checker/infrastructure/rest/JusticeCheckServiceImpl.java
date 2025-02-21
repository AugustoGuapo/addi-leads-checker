package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.JusticeCheckService;
import org.springframework.stereotype.Service;
import util.DelaySimulator;

import java.util.Map;

@Service
public class JusticeCheckServiceImpl implements JusticeCheckService {
    public static final Map<String, Boolean> STATUS_MAP = Map.of(
            "123456789", true,
            "987654321", false,
            "456789123", true,
            "321654987", false,
            "789123456", true
    );
    @Override
    public Boolean checkBackground(String identificationNumber) {
        DelaySimulator.simulateDelay();
        if(!STATUS_MAP.containsKey(identificationNumber)) {
            throw new IllegalArgumentException("Identification number not found");
        }
        return STATUS_MAP.get(identificationNumber);
    }
}
