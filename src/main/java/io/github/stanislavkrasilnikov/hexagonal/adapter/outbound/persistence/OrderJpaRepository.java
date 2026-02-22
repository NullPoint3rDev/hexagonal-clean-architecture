package io.github.stanislavkrasilnikov.hexagonal.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {

}
