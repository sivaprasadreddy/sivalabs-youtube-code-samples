package com.sivalabs.myapp.domain;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Customer with id="+customerId+" not found");
    }
}
