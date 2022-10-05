package com.burakkirbag.com.readingisgood.adapters;

import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrder;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetByLogin;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.model.OrderState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerFakeDataAdapter implements CustomerPort {

    @Override
    public Customer retrieve(String id) {
        if (id == "9999") return null;

        return Customer.builder()
                .id(id)
                .firstName("firstname")
                .lastName("lastname")
                .email("email")
                .password("password")
                .build();
    }

    @Override
    public Customer getByEmail(String email) {
        return Customer.builder()
                .id(email)
                .firstName("firstname")
                .lastName("lastname")
                .email(email)
                .email("password")
                .build();
    }

    @Override
    public Customer getByLogin(CustomerGetByLogin customerGetByLogin) {

        if (customerGetByLogin.getEmail().equals("fail")) return null;

        return Customer.builder()
                .id(customerGetByLogin.getEmail())
                .firstName("firstname")
                .lastName("lastname")
                .email(customerGetByLogin.getEmail())
                .email(customerGetByLogin.getPassword())
                .build();
    }

    @Override
    public Customer create(CustomerCreate customerCreate) {
        return Customer.builder()
                .id("1")
                .firstName(customerCreate.getFirstName())
                .lastName(customerCreate.getLastName())
                .email(customerCreate.getEmail())
                .password(customerCreate.getPassword())
                .build();
    }

    @Override
    public PagedList<Order> getOrders(CustomerGetAllOrder customerGetAllOrder) {

        var orders = new ArrayList<Order>();

        for (int i = 1; i < 100; i++) {
            Order.builder()
                    .id(String.valueOf(i))
                    .totalPrice(BigDecimal.valueOf(25).multiply(BigDecimal.valueOf(i)))
                    .state(OrderState.NEW)
                    .createdAt(LocalDateTime.now())
                    .build();
        }

        return new PagedList<Order>(orders, 1, 5, orders.stream().count());
    }

    @Override
    public Boolean exist(String email) {
        if (email.equals("unique")) return false;
        return true;
    }
}
