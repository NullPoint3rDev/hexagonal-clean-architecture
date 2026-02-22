package io.github.stanislavkrasilnikov.hexagonal.application;

import io.github.stanislavkrasilnikov.hexagonal.domain.Order;
import io.github.stanislavkrasilnikov.hexagonal.domain.port.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        createOrderUseCase = new CreateOrderUseCase(orderRepositoryPort);
    }

    @Test
    void create_savesOrderAndReturnsIt() {
        List<String> productIds = List.of("p1", "p2");
        int amount = 2;
        Order savedOrder = new Order("generated-id", productIds, amount);
        when(orderRepositoryPort.save(any(Order.class))).thenReturn(savedOrder);

        Order result = createOrderUseCase.create(productIds, amount);

        assertThat(result).isNotNull();
        assertThat(result.getOrderId()).isEqualTo("generated-id");
        assertThat(result.getProducts()).containsExactly("p1", "p2");
        assertThat(result.getAmount()).isEqualTo(2);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepositoryPort).save(orderCaptor.capture());
        Order captured = orderCaptor.getValue();
        assertThat(captured.getProducts()).containsExactly("p1", "p2");
        assertThat(captured.getAmount()).isEqualTo(2);
        assertThat(captured.getOrderId()).isNotBlank();
    }
}
