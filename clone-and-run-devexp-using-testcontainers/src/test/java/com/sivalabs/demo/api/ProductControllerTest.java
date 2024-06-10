package com.sivalabs.demo.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.sivalabs.demo.ContainersConfig;
import com.sivalabs.demo.domain.Product;
import com.sivalabs.demo.domain.ProductRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

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
    void shouldGetProductByCode() {
        Product product = restTemplate.getForObject("/api/products/{code}", Product.class, productCode);
        assertThat(product.getCode()).isEqualTo(productCode);
        assertThat(product.getPrice().compareTo(BigDecimal.TEN) == 0).isTrue();
    }
}
