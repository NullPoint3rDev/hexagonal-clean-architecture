package io.github.stanislavkrasilnikov.hexagonal.adapter.outbound.persistence;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = toEntity(order);
        OrderEntity saved = orderJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return orderJpaRepository.findById(orderId).map(this::toDomain);
    }

    private OrderEntity toEntity(Order order) {
        String productsAsString = String.join(",", order.getProducts());
        return new OrderEntity(order.getOrderId(), productsAsString, order.getAmount());
    }

    private Order toDomain(OrderEntity entity) {
        String ps = entity.getProducts();
        List<String> products = (ps == null || ps.isEmpty())
                ? List.of()
                : List.of(ps.split(","));
        return new Order(entity.getOrderId(), products, entity.getAmount());
    }
}
