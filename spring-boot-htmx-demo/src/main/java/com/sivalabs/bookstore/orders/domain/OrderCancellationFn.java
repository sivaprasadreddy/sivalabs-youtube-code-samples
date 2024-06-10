package com.sivalabs.bookstore.orders.domain;

import com.sivalabs.bookstore.orders.domain.models.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service("orderCancellationFn")
@Transactional
@Description("Cancel the order for a given order number")
@RequiredArgsConstructor
@Slf4j
class OrderCancellationFn implements
        Function<OrderCancellationFn.Request, OrderCancellationFn.Response> {
    private final SecurityService securityService;
    private final OrderRepository orderRepository;

    @Override
    public Response apply(Request request) {
        log.info("Cancel the order for OrderNumber: {}", request.orderNumber());
        String userName = securityService.getLoginUserName();
        String status = orderRepository.findByUserNameAndOrderNumber(userName, request.orderNumber())
                .map(o -> {
                    orderRepository.updateOrderStatus(request.orderNumber(), OrderStatus.CANCELLED);
                    return "Order Cancelled";
                }).orElse("Order not found");
        return new Response(status);
    }

    public record Request(String orderNumber) {}
    public record Response(String status) {}
}
