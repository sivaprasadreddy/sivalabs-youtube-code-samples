package com.sivalabs.demo.events;

import static com.sivalabs.demo.events.ProductEventListener.PRODUCT_PRICE_CHANGES_TOPIC;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.sivalabs.demo.ContainersConfig;
import com.sivalabs.demo.domain.Product;
import com.sivalabs.demo.domain.ProductRepository;
import com.sivalabs.demo.events.ProductEventListener.ProductPriceChangedEvent;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
class ProductEventListenerTest {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ProductRepository productRepository;

    String productCode;

    @BeforeEach
    void setUp() {
        productCode = UUID.randomUUID().toString();
        Product product = new Product(null, productCode, "Test Product", BigDecimal.TEN);
        productRepository.save(product);
    }

    @Test
    void shouldHandleProductPriceChangedEvent() {
        BigDecimal newPrice = new BigDecimal(57);
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productCode, newPrice);

        kafkaTemplate.send(PRODUCT_PRICE_CHANGES_TOPIC, event);

        await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS).untilAsserted(() -> {
            Optional<Product> optionalProduct = productRepository.findByCode(productCode);
            assertThat(optionalProduct).isPresent();
            assertThat(optionalProduct.get().getCode()).isEqualTo(productCode);
            assertThat(optionalProduct.get().getPrice().compareTo(newPrice) == 0)
                    .isTrue();
        });
    }
}
