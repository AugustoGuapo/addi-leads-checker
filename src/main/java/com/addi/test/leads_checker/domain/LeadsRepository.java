package com.addi.test.leads_checker.domain;

import java.util.Optional;

public interface LeadsRepository {
    Optional<Lead> findById(String id);
}
