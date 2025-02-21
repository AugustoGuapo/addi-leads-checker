package com.addi.test.leads_checker.application;

import com.addi.test.leads_checker.domain.InternalScoringService;
import com.addi.test.leads_checker.domain.JusticeCheckService;
import com.addi.test.leads_checker.domain.Lead;
import com.addi.test.leads_checker.domain.LeadsService;
import com.addi.test.leads_checker.domain.NationalIdentificationService;
import com.addi.test.leads_checker.domain.PersonDTO;
import com.addi.test.leads_checker.domain.Verification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutomatedChecksServiceTest {

    @Mock
    private LeadsService leadsService;
    @Mock
    private NationalIdentificationService identificationService;
    @Mock
    private JusticeCheckService justiceCheckService;
    @Mock
    private InternalScoringService scoringService;
    @InjectMocks
    private AutomatedChecksService automatedChecksService;


    @Test
    void verifyReturnsCorrectVerificationWhenAllChecksPass() throws Exception {
        Lead lead = mock(Lead.class);
        when(leadsService.findById(1L)).thenReturn(Optional.of(lead));
        when(lead.nationalIdNumber()).thenReturn("123456789");
        when(lead.dateOfBirth()).thenReturn(LocalDate.of(2000, 1, 1));
        when(lead.firstName()).thenReturn("John");
        when(lead.lastName()).thenReturn("Doe");
        when(identificationService.verifyPerson(any(PersonDTO.class))).thenReturn(true);
        when(justiceCheckService.checkBackground("123456789")).thenReturn(true);
        when(scoringService.calculateLeadScore(lead)).thenReturn(100);

        Verification verification = automatedChecksService.verify(1L);

        assertTrue(verification.isBackgroundClean());
        assertTrue(verification.personExists());
        assertEquals(100, verification.score());
    }

    @Test
    void verifyReturnsFalseVerificationWhenPersonVerificationFails() throws Exception {
        Lead lead = mock(Lead.class);
        when(leadsService.findById(1L)).thenReturn(Optional.of(lead));
        when(lead.nationalIdNumber()).thenReturn("123456789");
        when(lead.dateOfBirth()).thenReturn(LocalDate.of(2000, 1, 1));
        when(lead.firstName()).thenReturn("John");
        when(lead.lastName()).thenReturn("Doe");
        when(identificationService.verifyPerson(any(PersonDTO.class))).thenReturn(false);
        when(justiceCheckService.checkBackground("123456789")).thenReturn(true);

        Verification verification = automatedChecksService.verify(1L);

        assertTrue(verification.isBackgroundClean());
        assertFalse(verification.personExists());
        assertNull(verification.score());
    }

    @Test
    void verifyReturnsFalseVerificationWhenBackgroundCheckFails() throws Exception {
        Lead lead = mock(Lead.class);
        when(leadsService.findById(1L)).thenReturn(Optional.of(lead));
        when(lead.nationalIdNumber()).thenReturn("123456789");
        when(lead.dateOfBirth()).thenReturn(LocalDate.of(2000, 1, 1));
        when(lead.firstName()).thenReturn("John");
        when(lead.lastName()).thenReturn("Doe");
        when(identificationService.verifyPerson(any(PersonDTO.class))).thenReturn(true);
        when(justiceCheckService.checkBackground("123456789")).thenReturn(false);

        Verification verification = automatedChecksService.verify(1L);

        assertTrue(verification.personExists());
        assertFalse(verification.isBackgroundClean());
        assertNull(verification.score());
    }

    @Test
    void verifyReturnsFalseVerificationWhenLeadNotFound() {
        when(leadsService.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> automatedChecksService.verify(1L));
    }

    @Test
    void verifyReturnsFalseVerificationWhenExceptionOccurs() throws Exception {
        Lead lead = mock(Lead.class);
        when(leadsService.findById(1L)).thenReturn(Optional.of(lead));
        when(lead.nationalIdNumber()).thenReturn("123456789");
        when(lead.dateOfBirth()).thenReturn(LocalDate.of(2000, 1, 1));
        when(lead.firstName()).thenReturn("John");
        when(lead.lastName()).thenReturn("Doe");
        when(identificationService.verifyPerson(any(PersonDTO.class))).thenThrow(new RuntimeException("Test Exception"));
        when(justiceCheckService.checkBackground("123456789")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> automatedChecksService.verify(1L));
    }
}