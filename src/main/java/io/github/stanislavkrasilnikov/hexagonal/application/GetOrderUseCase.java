package io.github.stanislavkrasilnikov.hexagonal.application;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.GetOrderPort;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOrderUseCase implements GetOrderPort {

    private final OrderRepositoryPort orderRepositoryPort;

    public GetOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Optional<Order> getById(String orderId) {
        return orderRepositoryPort.findById(orderId);
    }
}
