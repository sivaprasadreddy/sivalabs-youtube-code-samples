package com.sivalabs.bookstore.orders.domain;

import com.sivalabs.bookstore.catalog.ProductService;
import com.sivalabs.bookstore.orders.domain.events.OrderCreatedEvent;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderRequest;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderResponse;
import com.sivalabs.bookstore.orders.domain.models.OrderDTO;
import com.sivalabs.bookstore.orders.domain.models.OrderSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ApplicationEventPublisher eventPublisher;

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        validate(request);
        OrderEntity newOrder = OrderMapper.convertToEntity(request);
        OrderEntity savedOrder = this.orderRepository.save(newOrder);
        log.info("Created Order with orderNumber={}", savedOrder.getOrderNumber());
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getOrderNumber(),
                savedOrder.getOrderItem().code(),
                savedOrder.getOrderItem().quantity()
        );
        eventPublisher.publishEvent(event);
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }

    private void validate(CreateOrderRequest request) {
        String code = request.item().code();
        var product = productService.getByCode(code)
                .orElseThrow(() -> new InvalidOrderException("Product not found with code: " + code));
        if(product.price().compareTo(request.item().price()) != 0) {
            throw new InvalidOrderException("Product price mismatch");
        }
    }

    public List<OrderSummary> findOrders() {
        Sort sort = Sort.by("id").descending();
        return orderRepository.findAllBy(sort);
    }

    public Optional<OrderDTO> findOrder(String orderNumber) {
        return orderRepository
                .findByOrderNumber(orderNumber)
                .map(OrderMapper::convertToDTO);
    }
}
