package com.sivalabs.myapp.repositories;

import com.sivalabs.myapp.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmailAndPasswordAndActiveIsTrue(String email, String password);
}
