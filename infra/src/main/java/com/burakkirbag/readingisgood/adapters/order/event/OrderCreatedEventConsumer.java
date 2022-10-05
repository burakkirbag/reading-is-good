package com.burakkirbag.readingisgood.adapters.order.event;

import com.burakkirbag.readingisgood.common.usecase.BeanAwareUseCasePublisher;
import com.burakkirbag.readingisgood.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCreatedEventConsumer extends BeanAwareUseCasePublisher {

    @EventListener
    public void consume(OrderCreatedEvent orderCreatedEvent) {
        publish(orderCreatedEvent.toModel());
    }
}
