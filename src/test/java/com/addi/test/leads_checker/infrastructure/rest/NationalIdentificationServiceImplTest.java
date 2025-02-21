package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.PersonDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NationalIdentificationServiceImplTest {

    private final NationalIdentificationServiceImpl service = new NationalIdentificationServiceImpl();

    @Test
    void verifyPersonReturnsTrueForValidPerson() {
        PersonDTO person = new PersonDTO("123456789", LocalDate.of(1990, 5, 15), "John", "Doe");
        assertTrue(service.verifyPerson(person));
    }

    @Test
    void verifyPersonReturnsFalseForInvalidPerson() {
        PersonDTO person = new PersonDTO("123456789", LocalDate.of(1990, 5, 15), "John", "Smith");
        assertFalse(service.verifyPerson(person));
    }

    @Test
    void verifyPersonThrowsExceptionForUnknownPerson() {
        PersonDTO person = new PersonDTO("000000000", LocalDate.of(1990, 5, 15), "Unknown", "Person");
        assertThrows(IllegalArgumentException.class, () -> service.verifyPerson(person));
    }
}