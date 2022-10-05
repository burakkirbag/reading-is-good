package com.burakkirbag.readingisgood.order.service;

import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import com.burakkirbag.readingisgood.order.model.OrderState;
import com.burakkirbag.readingisgood.order.usecase.OrderCreate;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@DomainComponent
public class OrderValidator {

    private BookPort bookPort;

    public OrderValidator(BookPort bookPort) {
        this.bookPort = bookPort;
    }

    public void validate(OrderCreate orderCreate, Customer customer) {

        var books = bookPort.retrieve(
                orderCreate.getItems()
                        .stream().map((b) -> b.getId())
                        .collect(Collectors.toList()));

        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);

        orderCreate.getItems().stream().forEach((item) -> {

            if (item == null) {
                throw new BusinessException("orderapi.create.notFoundItem");
            }

            if (item.getQuantity() < 1) {
                throw new BusinessException("orderapi.create.invalidQuantity");
            }

            var book = books.stream().filter(b -> b.getId().equals(item.getId())).findFirst()
                    .orElseThrow(() -> new BusinessException("orderapi.create.notFoundItem"));

            if (book.getStock() > 0) {
                if (book.getStock() < item.getQuantity()) {
                    throw new BusinessException("orderapi.create.insufficientStock");
                }
            } else {
                throw new BusinessException("orderapi.create.outOfStock");
            }

            if (book.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
                throw new BusinessException("orderapi.create.invalidBookPrice");
            }

            item.setName(book.getName());
            item.setUnitPrice(book.getPrice());

            totalPrice.updateAndGet(x -> x.add(item.getTotalPrice()));
        });

        orderCreate.setTotalPrice(totalPrice.get());
        orderCreate.setCustomer(OrderCustomer.from(customer));
        orderCreate.setState(OrderState.PENDING);

        if (orderCreate.getTotalPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new BusinessException("orderapi.create.invalidOrderPrice");
        }
    }
}