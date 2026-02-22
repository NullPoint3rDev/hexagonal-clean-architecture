package io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest.dto;

import java.util.List;

public class OrderResponse {
    private String orderId;
    private List<String> products;
    private int amount;

    public OrderResponse(String orderId, List<String> products, int amount) {
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
