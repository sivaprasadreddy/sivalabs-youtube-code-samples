package com.sivalabs.di;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

class LoanServiceTest {

    LoanService loanService;
    IBackgroundVerificationService bgvService;

    @BeforeEach
    void setUp() {
        bgvService = Mockito.mock(IBackgroundVerificationService.class);
        loanService = new LoanService(bgvService);
    }

    @Test
    void shouldApproveLoanWhenScoreIsLessThan3() {
        LoanRequest request = new LoanRequest();
        request.uniqueId = "P1000";

        given(bgvService.getScore("P1000")).willReturn(1);

        boolean approved = loanService.applyForLoan(request);
        assertTrue(approved);
    }

    @Test
    void shouldRejectLoanWhenScoreIsGreaterThan2() {
        LoanRequest request = new LoanRequest();
        request.uniqueId = "P1000";

        given(bgvService.getScore("P1000")).willReturn(3);
        boolean approved = loanService.applyForLoan(request);
        assertFalse(approved);
    }
}