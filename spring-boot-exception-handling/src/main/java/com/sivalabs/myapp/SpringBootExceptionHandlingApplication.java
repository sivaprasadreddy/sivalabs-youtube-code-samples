package com.sivalabs.myapp;

import com.sivalabs.myapp.domain.Customer;
import com.sivalabs.myapp.domain.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootExceptionHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExceptionHandlingApplication.class, args);
	}

}

@Component
@RequiredArgsConstructor
class DataInitializer implements CommandLineRunner {
	private final CustomerService customerService;

	@Override
	public void run(String... args) {
		customerService.createCustomer(new Customer(null, "Siva", "siva@gmail.com"));
		customerService.createCustomer(new Customer(null, "Neha", "neha@gmail.com"));
	}
}

