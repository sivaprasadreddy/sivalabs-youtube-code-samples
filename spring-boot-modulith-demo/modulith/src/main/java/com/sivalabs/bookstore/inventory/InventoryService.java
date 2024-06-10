package com.sivalabs.bookstore.inventory;

import com.sivalabs.bookstore.orders.domain.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class InventoryService {

    @EventListener
    void on(OrderCreatedEvent event) {
      log.info("Update inventory for product code: {}", event.productCode());
    }

}
