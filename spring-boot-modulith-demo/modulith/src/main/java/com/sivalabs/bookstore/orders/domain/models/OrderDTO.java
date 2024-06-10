package com.sivalabs.bookstore.orders.domain.models;

import java.time.LocalDateTime;

public record OrderDTO(
        String orderNumber,
        OrderItem item,
        Customer customer,
        OrderStatus status,
        LocalDateTime createdAt) {
}
