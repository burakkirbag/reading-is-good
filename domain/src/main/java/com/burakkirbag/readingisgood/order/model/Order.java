package com.burakkirbag.readingisgood.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {

    private String id;

    private BigDecimal totalPrice;

    private String state;

    private LocalDateTime createdAt;

    private OrderCustomer customer;

    private List<OrderItem> items;
}
