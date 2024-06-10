package com.sivalabs.aop;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class CustomerService implements  ICustomerService {

    @Transactional
    public Customer createCustomer(Customer customer) {
        if(customer.id != 0) {
            throw new RuntimeException("Invalid Customer Id");
        }
        System.out.println("Create customer");
        updateCustomer(customer);

        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateCustomer(Customer customer) {
        System.out.println("Update customer");
    }
}
