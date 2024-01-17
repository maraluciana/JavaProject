package com.project.demo.dto;

import java.util.UUID;

public class CartItemRequestDTO {
    private UUID flowerId;
    private int quantity;

    public UUID getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(UUID flowerId) {
        this.flowerId = flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
