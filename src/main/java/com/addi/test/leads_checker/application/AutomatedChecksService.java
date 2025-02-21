package com.addi.test.leads_checker.application;

import com.addi.test.leads_checker.domain.InternalScoringService;
import com.addi.test.leads_checker.domain.JusticeCheckService;
import com.addi.test.leads_checker.domain.LeadsService;
import com.addi.test.leads_checker.domain.NationalIdentificationService;
import com.addi.test.leads_checker.domain.PersonDTO;
import com.addi.test.leads_checker.domain.Verification;
import org.springframework.stereotype.Service;
import util.LeadStatuses;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AutomatedChecksService {

    private final LeadsService leadsService;
    private final NationalIdentificationService identificationService;
    private final JusticeCheckService justiceCheckService;
    private final InternalScoringService scoringService;

    public AutomatedChecksService(LeadsService leadsService,
                                  NationalIdentificationService identificationService,
                                  JusticeCheckService justiceCheckService,
                                  InternalScoringService scoringService) {
        this.leadsService = leadsService;
        this.identificationService = identificationService;
        this.justiceCheckService = justiceCheckService;
        this.scoringService = scoringService;
    }

    public Verification verify(Long leadId) {
        return leadsService.findById(leadId)
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
                        LeadStatuses status = (personVerified && cleanBackground && score >= 60) ? LeadStatuses.APPROVED : LeadStatuses.DENIED;
                        return new Verification(personVerified, cleanBackground, score, status);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getCause());
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("Lead not found"));
    }
}
