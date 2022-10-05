package com.burakkirbag.readingisgood.adapters.order.event;

import com.burakkirbag.readingisgood.order.event.OrderCreatedEvent;
import com.burakkirbag.readingisgood.order.port.OrderCreatedEventPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedEventAdapter implements OrderCreatedEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderCreatedEventAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent orderCreatedEvent) {
        applicationEventPublisher.publishEvent(orderCreatedEvent);
    }
}
