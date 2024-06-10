package com.sivalabs.di;

import org.springframework.stereotype.Component;

@Component
public class LoanService {
    private IBackgroundVerificationService bvService;

    public LoanService(IBackgroundVerificationService bvService) {
        this.bvService = bvService;
    }

    public boolean applyForLoan(LoanRequest loanRequest) {
        int score = bvService.getScore(loanRequest.uniqueId);
        if(score < 3) {
            System.out.println("Sanction loan");
            return true;
        } else {
            System.out.println("Reject loan. Reason: Poor credibility score");
            return false;
        }
    }
}
