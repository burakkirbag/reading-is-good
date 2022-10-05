package com.burakkirbag.readingisgood.order.port;

import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.usecase.OrderCreate;
import com.burakkirbag.readingisgood.order.usecase.OrderGetAllByDateInterval;

public interface OrderPort {

    Order retrieve(String id);

    Order create(OrderCreate orderCreate);

    PagedList<Order> getAllByCustomerId(String customerId, Integer size, Integer page);

    PagedList<Order> getAllByDateInterval(OrderGetAllByDateInterval orderGetAllByDateInterval);

    void updateState(String id, String state);
}
