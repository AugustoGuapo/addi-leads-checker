package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.Lead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LeadsServiceImplTest {

    LeadsServiceImpl leadsService;

    @BeforeEach
    void setUp() {
        leadsService = new LeadsServiceImpl();
    }

    @Test
    void shouldReturnSomethingInsideOptional() {
        Optional<Lead> lead = leadsService.findById(1L);
        assertTrue(lead.isPresent());
    }

    @Test
    void shouldReturnEmptyOptional() {
        Optional<Lead> lead = leadsService.findById(0L);
        assertTrue(lead.isEmpty());
    }

}