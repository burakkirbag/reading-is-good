package com.burakkirbag.readingisgood.order.event;

import com.burakkirbag.readingisgood.common.model.Event;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.usecase.OrderCheck;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderCreatedEvent implements Event {

    private LocalDateTime eventCreatedAt;
    private String id;
    private String customerId;

    public OrderCheck toModel() {
        return OrderCheck.builder()
                .id(id)
                .customerId(customerId)
                .build();
    }

    public static OrderCreatedEvent from(Order order) {
        return OrderCreatedEvent.builder()
                .eventCreatedAt(LocalDateTime.now())
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .build();
    }
}
