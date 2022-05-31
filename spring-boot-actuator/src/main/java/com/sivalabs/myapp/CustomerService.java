package com.sivalabs.myapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        log.debug("----getAllCustomers----");
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {"customer-by-id"})
    public Customer getCustomerById(Long id) {
        log.debug("Get customer by id: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
