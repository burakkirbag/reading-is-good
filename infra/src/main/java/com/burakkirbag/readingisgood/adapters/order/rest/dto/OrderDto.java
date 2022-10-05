package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {

    private String id;

    private BigDecimal totalPrice;

    private String state;

    private LocalDateTime createdAt;

    private OrderCustomerDto customer;

    public static OrderDto fromModel(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .state(order.getState())
                .createdAt(order.getCreatedAt())
                .customer(OrderCustomerDto.fromModel(order.getCustomer()))
                .build();
    }
}
