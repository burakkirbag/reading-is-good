package com.burakkirbag.readingisgood.order.event;

import com.burakkirbag.readingisgood.common.model.Event;
import com.burakkirbag.readingisgood.order.model.Order;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderUpdatedToNewStateEvent implements Event {

    private LocalDateTime eventCreatedAt;
    private String id;

    public static OrderUpdatedToNewStateEvent from(Order order) {
        return OrderUpdatedToNewStateEvent.builder()
                .eventCreatedAt(LocalDateTime.now())
                .id(order.getId())
                .build();
    }
}
