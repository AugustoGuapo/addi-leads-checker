package com.addi.test.leads_checker.infrastructure.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JusticeCheckServiceImplTest {

    JusticeCheckServiceImpl justiceCheckService;

    @Test
    void shouldJusticeCheckReturnTrue() {
        justiceCheckService = new JusticeCheckServiceImpl();
        Boolean result = justiceCheckService.checkBackground("123456789");
        assertTrue(result);
    }

    @Test
    void shouldJusticeCheckReturnFalse() {
        justiceCheckService = new JusticeCheckServiceImpl();
        Boolean result = justiceCheckService.checkBackground("987654321");
        assertFalse(result);
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        justiceCheckService = new JusticeCheckServiceImpl();
        assertThrows(IllegalArgumentException.class, () -> justiceCheckService.checkBackground("1234567890"));
    }

}