package com.sivalabs.aop;

public class PersonService {

    public Person createPerson(Person customer) {
        System.out.println("Create person");
        return customer;
    }

    public void updatePerson(Person person) {
        System.out.println("Update Person");
    }

}
