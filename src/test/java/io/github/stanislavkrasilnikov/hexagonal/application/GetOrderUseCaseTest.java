package io.github.stanislavkrasilnikov.hexagonal.application;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderUseCaseTest {

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    private GetOrderUseCase getOrderUseCase;

    @BeforeEach
    void setUp() {
        getOrderUseCase = new GetOrderUseCase(orderRepositoryPort);
    }

    @Test
    void getById_returnsOrderWhenFound() {
        String orderId = "order-1";
        Order order = new Order(orderId, List.of("p1"), 1);
        when(orderRepositoryPort.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = getOrderUseCase.getById(orderId);

        assertThat(result).isPresent();
        assertThat(result.get().getOrderId()).isEqualTo(orderId);
        assertThat(result.get().getProducts()).containsExactly("p1");
        assertThat(result.get().getAmount()).isEqualTo(1);
        verify(orderRepositoryPort).findById(orderId);
    }

    @Test
    void getById_returnsEmptyWhenNotFound() {
        String orderId = "missing";
        when(orderRepositoryPort.findById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = getOrderUseCase.getById(orderId);

        assertThat(result).isEmpty();
        verify(orderRepositoryPort).findById(orderId);
    }
}
