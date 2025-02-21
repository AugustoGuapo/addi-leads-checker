package com.addi.test.leads_checker.infrastructure.controller;

import com.addi.test.leads_checker.application.AutomatedChecksService;
import com.addi.test.leads_checker.configuration.ObjectMapperConfig;
import com.addi.test.leads_checker.domain.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import util.LeadStatuses;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VerificationLeadsController.class)
@Import(ObjectMapperConfig.class)
class VerificationLeadsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutomatedChecksService automatedChecksService;

    @Test
    void verifyLeadReturnsOkWhenVerificationSucceeds() throws Exception {
        Verification verification = new Verification(true, true, 100, LeadStatuses.APPROVED);
        when(automatedChecksService.verify(1)).thenReturn(verification);

        mockMvc.perform(get("/leads/1/verify")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"person_exists\":true,\"is_background_clean\":true,\"score\":100, \"status\":\"APPROVED\"}"));
    }

    @Test
    void verifyLeadReturnsOkWhenVerificationFails() throws Exception {
        Verification verification = new Verification(false, false, null, LeadStatuses.DENIED);
        when(automatedChecksService.verify(1)).thenReturn(verification);

        mockMvc.perform(get("/leads/1/verify")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"person_exists\":false,\"is_background_clean\":false,\"score\":null, \"status\":\"DENIED\"}"));
    }

    @Test
    void verifyLeadHandlesExceptionGracefully() throws Exception {
        when(automatedChecksService.verify(1)).thenThrow(new RuntimeException("Test Exception"));

        mockMvc.perform(get("/leads/1/verify")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}