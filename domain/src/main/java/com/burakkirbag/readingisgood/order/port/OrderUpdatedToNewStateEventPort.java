package com.burakkirbag.readingisgood.order.port;

import com.burakkirbag.readingisgood.common.event.EventPublisher;
import com.burakkirbag.readingisgood.order.event.OrderUpdatedToNewStateEvent;

public interface OrderUpdatedToNewStateEventPort extends EventPublisher<OrderUpdatedToNewStateEvent> {

    void publish(OrderUpdatedToNewStateEvent orderUpdatedToNewStateEvent);
}