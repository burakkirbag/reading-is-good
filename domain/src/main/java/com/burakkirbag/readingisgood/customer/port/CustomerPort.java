package com.burakkirbag.readingisgood.customer.port;

import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrder;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetByLogin;
import com.burakkirbag.readingisgood.order.model.Order;

public interface CustomerPort {

    Customer retrieve(String id);

    Customer getByEmail(String email);

    Customer getByLogin(CustomerGetByLogin customerGetByLogin);

    Customer create(CustomerCreate customerCreate);

    PagedList<Order> getOrders(CustomerGetAllOrder customerGetAllOrder);

    Boolean exist(String email);
}
