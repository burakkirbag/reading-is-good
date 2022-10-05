package com.burakkirbag.readingisgood.order.port;

import com.burakkirbag.readingisgood.common.event.EventPublisher;
import com.burakkirbag.readingisgood.order.event.OrderCreatedEvent;

public interface OrderCreatedEventPort extends EventPublisher<OrderCreatedEvent> {

    void publish(OrderCreatedEvent orderCreatedEvent);
}
