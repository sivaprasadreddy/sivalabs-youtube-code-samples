package com.sivalabs.bookstore.catalog.web.controllers;

import com.sivalabs.bookstore.catalog.domain.PagedResult;
import com.sivalabs.bookstore.catalog.domain.Product;
import com.sivalabs.bookstore.catalog.domain.ProductService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
class ProductController {
    private final ProductService productService;

    @GetMapping
    String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    String showProducts(@RequestParam(name = "page", defaultValue = "1") int page,
                        Model model) {
        log.info("Fetching products for page: {}", page);
        PagedResult<Product> productsPage = productService.getProducts(page);
        model.addAttribute("productsPage", productsPage);
        return "products";
    }

    @HxRequest
    @GetMapping("/partials/products")
    String getProductsPartial(@RequestParam(name = "page", defaultValue = "1") int page,
                                          Model model){
        log.info("Fetching products-partial for page: {}", page);
        PagedResult<Product> productsPage = productService.getProducts(page);
        model.addAttribute("productsPage", productsPage);
        return "partials/products";
    }
}
