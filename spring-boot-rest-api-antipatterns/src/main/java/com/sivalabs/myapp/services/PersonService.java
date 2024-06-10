package com.sivalabs.myapp.services;

import com.sivalabs.myapp.entities.Person;
import com.sivalabs.myapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll()
                .stream().filter(Person::isActive)
                .toList();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> login(String email, String password) {
        return personRepository.findByEmailAndPasswordAndActiveIsTrue(email, password);
    }
}
