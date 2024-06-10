package com.sivalabs.aop;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        //useHandwrittenProxy();
        //useJdkDynamicProxy();
        useCgLibDynamicProxy();
    }

    static void useCgLibDynamicProxy() {
        //CustomerService target = new CustomerService();
        PersonService target = new PersonService();

        TransactionalCglibProxy cglibProxy = new TransactionalCglibProxy();
        PersonService personServiceProxy = cglibProxy.createProxy(target);

        Person person = new Person();
        person.id = 10L;
        person.name = "Siva";
        personServiceProxy.createPerson(person);

    }

    static void useJdkDynamicProxy() {
        CustomerService target = new CustomerService();

        TransactionJdkProxy<?> transactionProxy = new TransactionJdkProxy<>(target);
        ICustomerService customerServiceProxy = (ICustomerService) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] { ICustomerService.class },
                transactionProxy);

        Customer customer = new Customer();
        customer.id = 0L;
        customer.name = "Siva";
        customerServiceProxy.createCustomer(customer);
    }




    static void useHandwrittenProxy() {
        CustomerService target = new CustomerService();
        CustomerServiceProxy proxy = new CustomerServiceProxy(target);
        Customer customer = new Customer();
        customer.id = 0L;
        customer.name = "Siva";

        proxy.createCustomer(customer);
    }
}
