package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDetailResponse {

    private String id;

    private BigDecimal totalPrice;

    private String state;

    private LocalDateTime createdAt;

    private OrderCustomerDto customer;

    private List<OrderItemDto> items;

    public static OrderDetailResponse fromModel(Order order) {
        return OrderDetailResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .state(order.getState())
                .createdAt(order.getCreatedAt())
                .customer(OrderCustomerDto.fromModel(order.getCustomer()))
                .items(OrderItemDto.fromModel(order.getItems()))
                .build();
    }
}
