package com.aniket.ordermanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
}
