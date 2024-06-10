package com.sivalabs.bookstore.orders.web.controllers;

import com.sivalabs.bookstore.orders.domain.models.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ModelAttribute("cartItemCount")
    public int cartItemCount(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        return cart == null ? 0 : cart.getItemCount();
    }
}
