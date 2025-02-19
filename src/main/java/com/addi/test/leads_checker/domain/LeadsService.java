package com.addi.test.leads_checker.domain;

import java.util.Optional;

public interface LeadsService {
    Optional<Lead> findById(Integer id);
}
