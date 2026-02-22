package io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest;

import io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest.dto.CreateOrderRequest;
import io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest.dto.OrderResponse;
import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.CreateOrderPort;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.GetOrderPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderPort createOrderPort;
    private final GetOrderPort getOrderPort;

    public OrderController(CreateOrderPort createOrderPort, GetOrderPort getOrderPort) {
        this.createOrderPort = createOrderPort;
        this.getOrderPort = getOrderPort;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest orderRequest) {
        Order order = createOrderPort.create(orderRequest.getProductIds(), orderRequest.getAmount());
        return ResponseEntity.status(201).body(toResponse(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable String id) {
        return getOrderPort.getById(id)
                .map(order -> ResponseEntity.ok(toResponse(order)))
                .orElse(ResponseEntity.notFound().build());
    }


    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getProducts(),
                order.getAmount()
        );
    }
}
