package io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest.dto.CreateOrderRequest;
import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.CreateOrderPort;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.GetOrderPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderPort createOrderPort;

    @MockBean
    private GetOrderPort getOrderPort;

    @Test
    void create_returns201AndOrderResponse() throws Exception {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setProductIds(List.of("p1", "p2"));
        request.setAmount(2);
        Order created = new Order("order-123", List.of("p1", "p2"), 2);
        when(createOrderPort.create(anyList(), anyInt())).thenReturn(created);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value("order-123"))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products[0]").value("p1"))
                .andExpect(jsonPath("$.products[1]").value("p2"))
                .andExpect(jsonPath("$.amount").value(2));

        verify(createOrderPort).create(List.of("p1", "p2"), 2);
    }

    @Test
    void getById_returns200WhenOrderExists() throws Exception {
        String orderId = "order-1";
        Order order = new Order(orderId, List.of("p1"), 1);
        when(getOrderPort.getById(orderId)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.products[0]").value("p1"))
                .andExpect(jsonPath("$.amount").value(1));

        verify(getOrderPort).getById(orderId);
    }

    @Test
    void getById_returns404WhenOrderNotFound() throws Exception {
        String orderId = "missing";
        when(getOrderPort.getById(orderId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isNotFound());

        verify(getOrderPort).getById(orderId);
    }
}
