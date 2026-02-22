package io.github.stanislavkrasilnikov.hexagonal.adapter.outbound.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String orderId;
    private String products;
    private int amount;

    public OrderEntity() {}

    public OrderEntity(String orderId, String products, int amount) {
        this.orderId = orderId;
        this.products = products;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
