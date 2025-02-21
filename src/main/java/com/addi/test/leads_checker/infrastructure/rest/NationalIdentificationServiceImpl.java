package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.NationalIdentificationService;
import com.addi.test.leads_checker.domain.PersonDTO;
import org.springframework.stereotype.Service;

@Service
public class NationalIdentificationServiceImpl implements NationalIdentificationService {
    @Override
    public Boolean verifyPerson(PersonDTO person) {
        return null;
    }
}
