package io.github.stanislavkrasilnikov.hexagonal.domain.port;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;

import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(String orderId);
}
