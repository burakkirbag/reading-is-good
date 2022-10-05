package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class OrderGetAllByDateIntervalUseCaseHandler implements UseCaseHandler<PagedList<Order>, OrderGetAllByDateInterval> {

    private final OrderPort orderPort;

    @Override
    public PagedList<Order> handle(OrderGetAllByDateInterval useCase) {
        return orderPort.getAllByDateInterval(useCase);
    }
}
