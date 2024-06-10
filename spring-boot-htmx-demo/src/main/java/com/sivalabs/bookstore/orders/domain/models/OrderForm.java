package com.sivalabs.bookstore.orders.domain.models;

import jakarta.validation.Valid;

public record OrderForm(@Valid Customer customer,
                 @Valid Address deliveryAddress) {
}
