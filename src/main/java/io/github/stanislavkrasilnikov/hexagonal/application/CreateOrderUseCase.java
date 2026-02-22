package io.github.stanislavkrasilnikov.hexagonal.application;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.CreateOrderPort;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreateOrderUseCase implements CreateOrderPort {

    private final OrderRepositoryPort orderRepositoryPort;

    public CreateOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order create(List<String> productIds, int amount) {
        String id = UUID.randomUUID().toString();
        Order order = new Order(id, productIds, amount);
        return orderRepositoryPort.save(order);
    }
}
