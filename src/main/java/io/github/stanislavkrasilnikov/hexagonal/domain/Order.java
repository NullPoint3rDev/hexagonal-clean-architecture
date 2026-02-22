package io.github.stanislavkrasilnikov.hexagonal.domain;

import java.util.List;

public class Order {
    private String orderId;
    private List<String> products;
    private int amount;

    public Order(String orderId, List<String> products, int amount) {
        this.orderId = orderId;
        this.products = products;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getProducts() {
        return products;
    }

    public int getAmount() {
        return amount;
    }
}
