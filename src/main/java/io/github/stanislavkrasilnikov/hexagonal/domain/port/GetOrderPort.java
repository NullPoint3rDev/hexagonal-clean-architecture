package io.github.stanislavkrasilnikov.hexagonal.domain.port;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;

import java.util.Optional;

public interface GetOrderPort {
    Optional<Order> getById(String orderId);
}
