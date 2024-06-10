package com.sivalabs.bookstore.orders.web.controllers;

import com.sivalabs.bookstore.catalog.domain.PagedResult;
import com.sivalabs.bookstore.orders.domain.models.Cart;
import com.sivalabs.bookstore.orders.domain.OrderNotFoundException;
import com.sivalabs.bookstore.orders.domain.OrderService;
import com.sivalabs.bookstore.orders.domain.SecurityService;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderRequest;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderResponse;
import com.sivalabs.bookstore.orders.domain.models.OrderDTO;
import com.sivalabs.bookstore.orders.domain.models.OrderForm;
import com.sivalabs.bookstore.orders.domain.models.OrderItem;
import com.sivalabs.bookstore.orders.domain.models.OrderSummary;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping("/orders")
    String createOrder(@Valid OrderForm orderForm, HttpSession session) {
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        Cart cart = (Cart) session.getAttribute("cart");
        Set<OrderItem> orderItems =
                cart.getItems().stream().map(li -> new OrderItem(li.getCode(), li.getName(), li.getPrice(), li.getQuantity()))
                        .collect(Collectors.toSet());

        CreateOrderRequest request = new CreateOrderRequest(orderItems, orderForm.customer(), orderForm.deliveryAddress());
        CreateOrderResponse orderResponse = orderService.createOrder(userName, request);
        session.removeAttribute("cart");
        return "redirect:/orders/" + orderResponse.orderNumber();
    }

    @GetMapping("/orders")
    String getOrders(@RequestParam(name = "page", defaultValue = "1") int page,
                     Model model,
                     HtmxRequest hxRequest) {
        fetchOrders(model, page);
        if(hxRequest.isHtmxRequest()) {
            return "partials/orders";
        }
        return "orders";
    }

    /*
    @GetMapping("/orders")
    String getOrders(@RequestParam(name = "page", defaultValue = "1") int page,
                     Model model) {
        fetchOrders(model, page);
        return "orders";
    }

    @HxRequest
    @GetMapping("/partials/orders")
    String getOrdersPartial(@RequestParam(name = "page", defaultValue = "1") int page,
                     Model model) {
        fetchOrders(model, page);
        return "partials/orders";
    }*/

    private void fetchOrders(Model model, int page) {
        String userName = securityService.getLoginUserName();
        log.info("Fetching partial orders for user: {}", userName);
        PagedResult<OrderSummary> orders = orderService.findOrders(userName, page);
        model.addAttribute("orders", orders.data());
        model.addAttribute("hasMore", orders.hasNext());
        model.addAttribute("nextPageNo", page+ 1);
    }

    @GetMapping(value = "/orders/{orderNumber}")
    String getOrder(@PathVariable(value = "orderNumber") String orderNumber,
                    Model model) {
        log.info("Fetching order by orderNumber: {}", orderNumber);
        String userName = securityService.getLoginUserName();
        OrderDTO orderDTO = orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
        model.addAttribute("order", orderDTO);
        return "order_details";
    }
}
