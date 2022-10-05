package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class OrderRetrieveUseCaseHandler implements UseCaseHandler<Order, OrderRetrieve> {

    private final OrderPort orderPort;

    @Override
    public Order handle(OrderRetrieve useCase) {
        return orderPort.retrieve(useCase.getId());
    }
}
