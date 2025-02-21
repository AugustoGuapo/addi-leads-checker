package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.InternalScoringService;
import com.addi.test.leads_checker.domain.Lead;
import org.springframework.stereotype.Service;
import util.DelaySimulator;

import java.util.Random;

@Service
public class InternalScoringServiceImpl implements InternalScoringService {

    private final static Integer MAX_SCORE = 100;

    @Override
    public Integer calculateLeadScore(Lead lead) {
        DelaySimulator.simulateDelay();
        return new Random().nextInt(MAX_SCORE+1);
    }
}
