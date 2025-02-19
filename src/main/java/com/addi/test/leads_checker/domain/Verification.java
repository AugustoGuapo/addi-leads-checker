package com.addi.test.leads_checker.domain;

public record Verification(Boolean personExists,
                           Boolean isBackgroundClean,
                           Integer score) {
}
