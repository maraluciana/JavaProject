package com.project.demo.model;

import com.project.demo.enums.Enums;

import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private UUID userId;
    List<CartItem> cartItems;
    private int totalPrice;
    private Enums.CartStatus cartStatus;
}
