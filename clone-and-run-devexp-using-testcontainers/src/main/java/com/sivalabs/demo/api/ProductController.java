package com.sivalabs.demo.api;

import com.sivalabs.demo.domain.Product;
import com.sivalabs.demo.domain.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getById(@PathVariable String code) {
        return repository.findByCode(code).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }
}
