package com.addi.test.leads_checker.domain;

import util.LeadStatuses;

public record Verification(Boolean personExists,
                           Boolean isBackgroundClean,
                           Integer score,
                           LeadStatuses status) {
}
