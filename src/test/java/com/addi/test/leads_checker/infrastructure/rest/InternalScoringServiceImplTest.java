package com.addi.test.leads_checker.infrastructure.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternalScoringServiceImplTest {

    InternalScoringServiceImpl scoringService;

    @Test
    void shouldScoreBeBetween0And100() {
        scoringService = new InternalScoringServiceImpl();
        Integer score = scoringService.calculateLeadScore(null);
        assertTrue(score >= 0 && score <= 100);
    }

}