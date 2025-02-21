package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.InternalScoringService;
import com.addi.test.leads_checker.domain.Lead;
import org.springframework.stereotype.Service;

@Service
public class InternalScoringServiceImpl implements InternalScoringService {

    @Override
    public Integer calculateLeadScore(Lead lead) {
        return 0;
    }
}
