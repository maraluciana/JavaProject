package com.project.demo.model;

import com.project.demo.enums.Enums;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private List<OrderItem> orderItems;
    private int totalPrice;
    private Date date;
    private Enums.OrderStatus orderStatus;
}
