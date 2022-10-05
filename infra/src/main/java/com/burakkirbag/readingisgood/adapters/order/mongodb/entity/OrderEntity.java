package com.burakkirbag.readingisgood.adapters.order.mongodb.entity;

import com.burakkirbag.readingisgood.common.entity.AbstractEntity;
import com.burakkirbag.readingisgood.order.model.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Document("orders")
public class OrderEntity extends AbstractEntity {

    private BigDecimal totalPrice;

    private String state;

    private OrderCustomerEntity customer;

    private List<OrderItemEntity> items;

    public Order toModel() {

        return Order.builder()
                .id(super.getId())
                .totalPrice(totalPrice)
                .state(state)
                .customer(customer.toModel())
                .createdAt(getCreatedAt())
                .items(items.stream().map((item) -> item.toModel()).collect(Collectors.toList()))
                .build();
    }
}
