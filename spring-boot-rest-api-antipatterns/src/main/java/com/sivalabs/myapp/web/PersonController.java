package com.sivalabs.myapp.web;

import com.sivalabs.myapp.entities.Person;
import com.sivalabs.myapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/api/get-all-persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/api/get-person-by-id/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/create-person")
    public Person createPerson(@RequestBody @Validated Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/api/update-person/{id}")
    public Person updatePerson(
            @PathVariable Long id,
            @RequestBody @Validated Person person) {
        person.setId(id);
        return personService.updatePerson(person);
    }

    @DeleteMapping("/api/delete-person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
