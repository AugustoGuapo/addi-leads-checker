package com.addi.test.leads_checker.domain;

import java.time.LocalDate;


public record PersonDTO(String identificationNumber,
                        LocalDate dateOfBirth,
                        String firstName,
                        String lastName) {
}
