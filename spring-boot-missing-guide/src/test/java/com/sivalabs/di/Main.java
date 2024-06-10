package com.sivalabs.di;

public class Main {
    public static void main(String[] args) {

        BeanFactory beanFactory = new BeanFactory();
        IBackgroundVerificationService bgvS = beanFactory.getBean(IBackgroundVerificationService.class);
        System.out.println(bgvS.getScore("P200"));

        LoanService loanService =  beanFactory.getBean(LoanService.class);

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
