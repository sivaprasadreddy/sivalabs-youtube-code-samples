package com.sivalabs.templatee;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo {
    JdbcTemplate jdbcTemplate;

    void createCustomer(Customer customer) {
        jdbcTemplate.update("insert into customers(name) values(?)", customer.name);
    }

    void updateCustomer(Customer customer) {
        jdbcTemplate.update("update customers set name = ? where id = ?", customer.name, customer.id);
    }
}
