package com.addi.test.leads_checker.domain;

import java.time.LocalDate;

public record Lead(Long id,
        String nationalIdNumber,
        LocalDate dateOfBirth,
        String firstName,
        String lastName,
        String email) {
}
