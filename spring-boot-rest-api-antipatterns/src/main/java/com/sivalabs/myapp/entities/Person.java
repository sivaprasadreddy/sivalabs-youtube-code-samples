package com.sivalabs.myapp.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "person_id_generator")
    @SequenceGenerator(name = "person_id_generator",
                       sequenceName = "person_id_seq",
                       allocationSize = 10)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Password is required")
    private String password;

    private LocalDate dob;

    private boolean active = true;

    @ElementCollection
    @CollectionTable(name="phone_numbers", joinColumns=@JoinColumn(name="owner_id"))
    @Column(name="phone_number")
    private Set<String> phones;

    public Person() {}

    public Person(String name, String email, String password,
                  LocalDate dob, boolean active, Set<String> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.active = active;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhone(Set<String> phones) {
        this.phones = phones;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}