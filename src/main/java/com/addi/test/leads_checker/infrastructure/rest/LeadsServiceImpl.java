package com.addi.test.leads_checker.infrastructure.rest;

import com.addi.test.leads_checker.domain.Lead;
import com.addi.test.leads_checker.domain.LeadsService;
import org.springframework.stereotype.Service;
import util.DelaySimulator;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class LeadsServiceImpl implements LeadsService {

    public static final Map<Long, Lead> LEADS = Map.of(
            1L, new Lead(1L, "123456789", LocalDate.of(1990, 5, 15), "Jon", "Doe", "john.doe@example.com"),
            2L, new Lead(2L, "987654321", LocalDate.of(1985, 8, 22), "Jane", "Smith", "jane.smith@example.com"),
            3L, new Lead(3L, "456789123", LocalDate.of(2000, 1, 10), "Michael", "Johnsonn", "michael.johnson@example.com"),
            4L, new Lead(4L, "321654987", LocalDate.of(1995, 12, 5), "Emily", "Davis", "emily.davis@example.com"),
            5L, new Lead(5L, "789123456", LocalDate.of(1988, 7, 30), "Daniel", "Martinez", "daniel.martinez@example.com")
    );

    @Override
    public Optional<Lead> findById(Long id) {
        DelaySimulator.simulateDelay();
        return Optional.ofNullable(LEADS.get(id));
    }
}
