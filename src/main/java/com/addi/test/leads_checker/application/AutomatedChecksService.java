package com.addi.test.leads_checker.application;

import com.addi.test.leads_checker.domain.InternalScoringService;
import com.addi.test.leads_checker.domain.JusticeCheckService;
import com.addi.test.leads_checker.domain.Lead;
import com.addi.test.leads_checker.domain.LeadsRepository;
import com.addi.test.leads_checker.domain.NationalIdentificationService;
import com.addi.test.leads_checker.domain.PersonDTO;
import com.addi.test.leads_checker.domain.Verification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AutomatedChecksService {

    LeadsRepository leadsRepository;
    NationalIdentificationService identificationService;
    JusticeCheckService justiceCheckService;
    InternalScoringService scoringService;

    public Verification verify(Integer leadId) {
        return leadsRepository.findById(leadId)
                .map(lead -> {
                    CompletableFuture<Boolean> personVerifiedFuture = CompletableFuture.supplyAsync(() ->
                            identificationService.verifyPerson(
                                    new PersonDTO(
                                            lead.nationalIdNumber(),
                                            lead.dateOfBirth(),
                                            lead.firstName(),
                                            lead.lastName()
                                    )
                            ));
                    CompletableFuture<Boolean> cleanBackgroundFuture = CompletableFuture.supplyAsync(() ->
                            justiceCheckService.checkBackground(lead.nationalIdNumber()));
                    try {
                        boolean personVerified = personVerifiedFuture.get();
                        boolean cleanBackground = cleanBackgroundFuture.get();
                        Integer score = (personVerified && cleanBackground) ? scoringService.calculateLeadScore(lead) : null;
                        return new Verification(personVerified, cleanBackground, score);
                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Error durante la verificación: " + e.getMessage()); // Loguea el error
                        return new Verification(false, false, null); // Retorna un Verification con valores por defecto
                    }
                })
                .orElse(new Verification(false, false, null));
    }
}
