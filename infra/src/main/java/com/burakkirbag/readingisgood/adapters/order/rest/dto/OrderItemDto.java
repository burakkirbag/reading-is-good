package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private String id;

    private String name;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    public static OrderItemDto fromModel(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .totalPrice(item.getTotalPrice())
                .build();
    }

    public static List<OrderItemDto> fromModel(List<OrderItem> items) {
        var dtoItems = new ArrayList<OrderItemDto>();
        items.forEach((item)->{
            dtoItems.add(fromModel(item));
        });
        return dtoItems;
    }
}
