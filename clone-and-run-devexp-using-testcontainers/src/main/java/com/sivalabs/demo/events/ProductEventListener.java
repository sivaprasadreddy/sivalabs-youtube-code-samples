package com.sivalabs.demo.events;

import com.sivalabs.demo.domain.ProductRepository;
import java.math.BigDecimal;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductEventListener {
    public static final String PRODUCT_PRICE_CHANGES_TOPIC = "product-price-changes";
    private final ProductRepository productRepository;

    public ProductEventListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = PRODUCT_PRICE_CHANGES_TOPIC, groupId = "demo")
    public void handle(ProductPriceChangedEvent event) {
        productRepository.updateProductPrice(event.productCode(), event.price());
    }

    public record ProductPriceChangedEvent(String productCode, BigDecimal price) {}
}
