package com.burakkirbag.readingisgood.common.event;

import com.burakkirbag.readingisgood.common.model.Event;

public interface EventPublisher<T extends Event> {

    void publish(T event);
}
