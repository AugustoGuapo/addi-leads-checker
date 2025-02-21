package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.NationalIdentificationService;
import com.addi.test.leads_checker.domain.PersonDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class NationalIdentificationServiceImpl implements NationalIdentificationService {

    public static final Map<String, PersonDTO> PEOPLE = Map.of(
            "123456789", new PersonDTO("123456789", LocalDate.of(1990, 5, 15), "John", "Doe"),
            "987654321", new PersonDTO("987654321", LocalDate.of(1985, 8, 22), "Jane", "Smith"),
            "456789123", new PersonDTO("456789123", LocalDate.of(2000, 1, 10), "Michael", "Johnson"),
            "321654987", new PersonDTO("321654987", LocalDate.of(1995, 12, 5), "Emily", "Davis"),
            "789123456", new PersonDTO("789123456", LocalDate.of(1988, 7, 30), "Daniel", "Martinez")
    );
    @Override
    public Boolean verifyPerson(PersonDTO person) {
        PersonDTO savedPerson = PEOPLE.get(person.identificationNumber());
        if (savedPerson != null) {
            return savedPerson.equals(person);
        }
        throw new IllegalArgumentException("Person not found");
    }
}
