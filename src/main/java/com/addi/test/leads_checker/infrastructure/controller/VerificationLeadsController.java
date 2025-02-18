package com.addi.test.leads_checker.infrastructure.controller;

import com.addi.test.leads_checker.application.AutomatedChecksService;
import com.addi.test.leads_checker.domain.Verification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VerificationLeadsController {

    public AutomatedChecksService automatedChecksService;

    @GetMapping("/leads/{leadId}/verify")
    public ResponseEntity<Verification> verifyLead(@PathVariable Integer leadId) {
        return ResponseEntity.ok(automatedChecksService.verify(leadId));
    }
}
