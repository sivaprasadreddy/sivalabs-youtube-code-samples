package com.sivalabs.bookstore.catalog.web.controllers;

import com.sivalabs.bookstore.catalog.domain.Product;
import com.sivalabs.bookstore.catalog.domain.ProductNotFoundException;
import com.sivalabs.bookstore.catalog.domain.ProductService;
import com.sivalabs.bookstore.common.models.PagedResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
class ProductController {
    private final ProductService productService;

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int page) {
        log.info("Fetching products for page: {}", page);
        return productService.getProducts(page);
    }

    @GetMapping("/{code}")
    Product getProductByCode(@PathVariable String code){
        log.info("Fetching product by code: {}", code);
        return productService.getByCode(code)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
