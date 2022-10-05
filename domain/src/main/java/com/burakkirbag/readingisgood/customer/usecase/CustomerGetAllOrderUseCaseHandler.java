package com.burakkirbag.readingisgood.customer.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.order.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class CustomerGetAllOrderUseCaseHandler implements UseCaseHandler<PagedList<Order>, CustomerGetAllOrder> {

    private final CustomerPort customerPort;

    @Override
    public PagedList<Order> handle(CustomerGetAllOrder useCase) {
        return customerPort.getOrders(useCase);
    }
}