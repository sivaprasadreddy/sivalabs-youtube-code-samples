package com.sivalabs.myapp.rest;

import com.sivalabs.myapp.domain.CustomerNotFoundException;
import com.sivalabs.myapp.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping()
    public List<String> getCustomerOrders(@RequestParam Long customerId) {
        throw new CustomerNotFoundException(customerId);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("No orders found for given customer Id", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
