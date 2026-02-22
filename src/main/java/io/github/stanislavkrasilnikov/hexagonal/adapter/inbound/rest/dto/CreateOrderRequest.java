package io.github.stanislavkrasilnikov.hexagonal.adapter.inbound.rest.dto;

import java.util.List;

public class CreateOrderRequest {
    private List<String> productIds;
    private int amount;

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
