package com.burakkirbag.readingisgood.adapters.order.event;

import com.burakkirbag.readingisgood.order.event.OrderUpdatedToNewStateEvent;
import com.burakkirbag.readingisgood.order.port.OrderUpdatedToNewStateEventPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderUpdatedToNewStateEventAdapter implements OrderUpdatedToNewStateEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderUpdatedToNewStateEventAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderUpdatedToNewStateEvent orderUpdatedToNewStateEvent) {
        applicationEventPublisher.publishEvent(orderUpdatedToNewStateEvent);
    }
}
