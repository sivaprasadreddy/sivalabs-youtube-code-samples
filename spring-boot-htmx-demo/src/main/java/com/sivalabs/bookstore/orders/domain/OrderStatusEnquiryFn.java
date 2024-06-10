package com.sivalabs.bookstore.orders.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("orderStatusEnquiryFn")
@Description("Get the order status for a given order number")
@RequiredArgsConstructor
@Slf4j
class OrderStatusEnquiryFn implements
        Function<OrderStatusEnquiryFn.Request, OrderStatusEnquiryFn.Response> {
    private final SecurityService securityService;
    private final OrderRepository orderRepository;

    @Override
    public Response apply(Request request) {
        log.info("Get order status for OrderNumber: {}", request.orderNumber());
        String userName = securityService.getLoginUserName();
        String status = orderRepository.findByUserNameAndOrderNumber(userName, request.orderNumber())
                .map(o -> o.getStatus().name()).orElse("UNKNOWN");
        return new Response(status);
    }

    public record Request(String orderNumber) {}
    public record Response(String status) {}
}
