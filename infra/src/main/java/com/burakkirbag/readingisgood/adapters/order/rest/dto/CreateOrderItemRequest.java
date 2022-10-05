package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.OrderItem;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateOrderItemRequest {

    @NotNull(message = "Sipariş vermek istediğiniz kitabın kimliğini belirtmelisiniz.")
    @NotEmpty(message = "Sipariş vermek istediğiniz kitabın kimliğini belirtmelisiniz.")
    private String id;

    @Min(value = 1, message = "Geçerli bir sipariş miktarı girmelisiniz.")
    private Integer quantity;

    public OrderItem toModel() {
        return OrderItem.builder()
                .id(getId())
                .quantity(quantity)
                .build();
    }
}
