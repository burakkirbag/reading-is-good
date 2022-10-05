package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.VoidUseCaseHandler;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.order.event.OrderCreatedEvent;
import com.burakkirbag.readingisgood.order.port.OrderCreatedEventPort;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import com.burakkirbag.readingisgood.order.service.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class OrderCreateUseCaseHandler implements VoidUseCaseHandler<OrderCreate> {

    private final CustomerPort customerPort;
    private final OrderPort orderPort;
    private final OrderValidator orderValidator;
    private final OrderCreatedEventPort orderCreatedEventPort;

    @Override
    public void handle(OrderCreate useCase) {

        var customer = customerPort.retrieve(useCase.getCustomer().getId());

        orderValidator.validate(useCase, customer);

        var order = orderPort.create(useCase);

        orderCreatedEventPort.publish(OrderCreatedEvent.from(order));
    }
}
