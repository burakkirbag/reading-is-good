package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import com.burakkirbag.readingisgood.order.usecase.OrderCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "Sipariş vermek istediğiniz kitapları seçmelisiniz.")
    @NotEmpty(message = "Sipariş vermek istediğiniz kitapları seçmelisiniz.")
    private List<CreateOrderItemRequest> items;

    public OrderCreate toModel(String customerId) {
        return OrderCreate.builder()
                .customer(OrderCustomer.builder().id(customerId).build())
                .items(items.stream().map((item) -> item.toModel()).collect(Collectors.toList()))
                .build();
    }

}
