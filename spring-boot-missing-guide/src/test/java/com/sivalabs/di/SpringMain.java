package com.sivalabs.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.sivalabs");
        LoanService loanService = ctx.getBean(LoanService.class);
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.uniqueId = "P100";
        loanRequest.personEmail = "siva@gmail.com";
        boolean approved = loanService.applyForLoan(loanRequest);
        if(approved) {
            System.out.println("Loan is approved");
        } else {
            System.out.println("Loan is rejected");
        }
    }
}
