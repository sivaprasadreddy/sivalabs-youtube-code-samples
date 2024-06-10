package com.sivalabs.bookstore.orders.web.controllers;

import com.sivalabs.bookstore.catalog.Product;
import com.sivalabs.bookstore.catalog.ProductService;
import com.sivalabs.bookstore.orders.domain.OrderService;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderRequest;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderResponse;
import com.sivalabs.bookstore.orders.domain.models.Customer;
import com.sivalabs.bookstore.orders.domain.models.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ApplicationModuleTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @MockBean
    ProductService productService;

    @BeforeEach
    void setUp() {
        Product product = new Product("P100", "The Hunger Games", "", "", new BigDecimal("34.0"));
        BDDMockito.given(productService.getByCode("P100")).willReturn(Optional.of(product));
    }

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "customer" : {
                            "name": "Siva",
                            "email": "siva@gmail.com",
                            "phone": "999999999"
                        },
                        "item":{
                                "code": "P100",
                                "name": "The Hunger Games",
                                "price": 34.0,
                                "quantity": 1
                        }
                    }
                    """)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldReturnNotFoundWhenOrderIdNotExist() throws Exception {
        mockMvc.perform(get("/api/orders/{orderNumber}", "non-existing-order-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetOrderSuccessfully() throws  Exception {
        CreateOrderRequest request = getCreateOrderRequest();
        CreateOrderResponse createOrderResponse = orderService.createOrder(request);

        mockMvc.perform(get("/api/orders/{orderNumber}", createOrderResponse.orderNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber", is(createOrderResponse.orderNumber())));
    }

    private static CreateOrderRequest getCreateOrderRequest() {
        Customer customer = new Customer("Siva", "siva@gmail.com", "99999999999");
        OrderItem item = new OrderItem("P100", "The Hunger Games", new BigDecimal("34.0"), 1);
        return new CreateOrderRequest(item, customer);
    }
}
