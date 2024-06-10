package com.sivalabs.bookstore.orders.web.controllers;

import com.sivalabs.bookstore.orders.domain.models.Cart;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
class CartController {

    @HxRequest
    @PostMapping("/add-to-cart")
    HtmxResponse addProductToCart(Cart.LineItem lineItem,
                                  Model model,
                                  HttpSession session){
        log.info("Adding product code:{} to cart", lineItem.getCode());
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }
        cart.addItem(lineItem);
        session.setAttribute("cart", cart);
        //model.addAttribute("cartItemCount", cart.getItemCount());
        //return "partials/cart-item-count";
        return HtmxResponse.builder()
                .view(new ModelAndView("partials/cart-item-count",
                        Map.of("cartItemCount", cart.getItemCount())))
                .build();
    }

    @GetMapping("/cart")
    String showCart(Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }
        model.addAttribute("cart", cart);
        return "cart";
    }

    @HxRequest
    @PostMapping("/update-cart")
    HtmxResponse updateCart(@RequestParam("code") String code,
                            @RequestParam("quantity") int quantity,
                            Model model,
                            HttpSession session){
        log.info("Updating cart code:{}, quantity:{}", code, quantity);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }
        cart.updateItemQuantity(code, quantity);
        session.setAttribute("cart", cart);
        boolean refresh = cart.getItemCount() == 0;
        if(refresh) {
            return HtmxResponse.builder().refresh().build();
        }
        return HtmxResponse.builder()
                .view(new ModelAndView("partials/cart", Map.of("cart", cart)))
                .view(new ModelAndView("partials/cart-item-count",
                        Map.of("cartItemCount", cart.getItemCount())))
                .build();
    }
}
