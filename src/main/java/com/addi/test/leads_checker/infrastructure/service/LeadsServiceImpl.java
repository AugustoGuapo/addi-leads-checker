package com.addi.test.leads_checker.infrastructure.service;

import com.addi.test.leads_checker.domain.Lead;
import com.addi.test.leads_checker.domain.LeadsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeadsServiceImpl implements LeadsService {
    @Override
    public Optional<Lead> findById(Integer id) {
        return Optional.empty();
    }
}
