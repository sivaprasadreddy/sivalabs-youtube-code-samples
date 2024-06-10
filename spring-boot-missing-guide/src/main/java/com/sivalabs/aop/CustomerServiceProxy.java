package com.sivalabs.aop;

public class CustomerServiceProxy {
    private final CustomerService delegate;

    public CustomerServiceProxy(CustomerService delegate) {
        this.delegate = delegate;
    }

    public Customer createCustomer(Customer customer) {
        try {
            System.out.println("Start transaction");
            Customer result = delegate.createCustomer(customer);
            System.out.println("Commit transaction");
            return result;
        } catch (RuntimeException e) {
            System.out.println("Rollback transaction");
            throw e;
        } finally {
            System.out.println("Close connection");
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            System.out.println("Start transaction");
            delegate.updateCustomer(customer);
            System.out.println("Commit transaction");
        } catch (RuntimeException e) {
            System.out.println("Rollback transaction");
            throw e;
        } finally {
            System.out.println("Close connection");
        }
    }

}
