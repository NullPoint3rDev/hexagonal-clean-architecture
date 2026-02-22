package io.github.stanislavkrasilnikov.hexagonal.domain.port;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;

import java.util.List;

public interface CreateOrderPort {
    Order create(List<String> productIds, int amount);
}
