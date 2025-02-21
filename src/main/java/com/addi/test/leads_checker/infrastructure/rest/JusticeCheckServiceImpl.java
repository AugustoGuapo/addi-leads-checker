package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.JusticeCheckService;
import org.springframework.stereotype.Service;

@Service
public class JusticeCheckServiceImpl implements JusticeCheckService {
    @Override
    public Boolean checkBackground(String identificationNumber) {
        return null;
    }
}
