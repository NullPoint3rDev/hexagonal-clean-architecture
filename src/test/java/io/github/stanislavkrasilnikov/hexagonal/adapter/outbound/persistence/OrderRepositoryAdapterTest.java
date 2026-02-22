package io.github.stanislavkrasilnikov.hexagonal.adapter.outbound.persistence;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class OrderRepositoryAdapterTest {

    @Autowired
    private OrderRepositoryPort orderRepositoryPort;

    @Test
    void save_and_findById_returnsSavedOrder() {
        Order order = new Order("order-1", List.of("p1", "p2"), 3);
        Order saved = orderRepositoryPort.save(order);

        assertThat(saved).isNotNull();
        assertThat(saved.getOrderId()).isEqualTo("order-1");
        assertThat(saved.getProducts()).containsExactly("p1", "p2");
        assertThat(saved.getAmount()).isEqualTo(3);

        Optional<Order> found = orderRepositoryPort.findById("order-1");
        assertThat(found).isPresent();
        assertThat(found.get().getOrderId()).isEqualTo("order-1");
        assertThat(found.get().getProducts()).containsExactly("p1", "p2");
        assertThat(found.get().getAmount()).isEqualTo(3);
    }

    @Test
    void findById_returnsEmptyWhenNotExists() {
        Optional<Order> found = orderRepositoryPort.findById("non-existent");
        assertThat(found).isEmpty();
    }
}
