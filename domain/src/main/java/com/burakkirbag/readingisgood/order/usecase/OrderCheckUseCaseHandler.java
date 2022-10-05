package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.common.usecase.ObservableUseCasePublisher;
import com.burakkirbag.readingisgood.common.usecase.VoidUseCaseHandler;
import com.burakkirbag.readingisgood.order.event.OrderUpdatedToNewStateEvent;
import com.burakkirbag.readingisgood.order.model.OrderItem;
import com.burakkirbag.readingisgood.order.model.OrderState;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import com.burakkirbag.readingisgood.order.port.OrderUpdatedToNewStateEventPort;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@DomainComponent
public class OrderCheckUseCaseHandler extends ObservableUseCasePublisher implements VoidUseCaseHandler<OrderCheck> {

    private final OrderPort orderPort;
    private final BookPort bookPort;

    private final OrderUpdatedToNewStateEventPort orderUpdatedToNewStateEventPort;

    public OrderCheckUseCaseHandler(OrderPort orderPort, BookPort bookPort, OrderUpdatedToNewStateEventPort orderUpdatedToNewStateEventPort) {
        this.orderPort = orderPort;
        this.bookPort = bookPort;
        this.orderUpdatedToNewStateEventPort = orderUpdatedToNewStateEventPort;
        register(OrderCheck.class, this);
    }

    @Override
    public void handle(OrderCheck useCase) {

        var order = orderPort.retrieve(useCase.getId());
        var books = bookPort.retrieve(
                order.getItems()
                        .stream().map((b) -> b.getId())
                        .collect(Collectors.toList()));

        AtomicBoolean booksStockIsValid = new AtomicBoolean(true);
        for (OrderItem item : order.getItems()) {

            var book = books.stream().filter(b -> b.getId().equals(item.getId())).findFirst()
                    .orElseThrow(() -> new BusinessException("orderapi.check.notFoundItem"));

            var isValidStock = checkAndUpdateStock(item, book);

            if (!isValidStock) {
                booksStockIsValid.set(false);
                break;
            }
        }

        if (booksStockIsValid.get()) {
            bookPort.updateStock(books);
            orderPort.updateState(order.getId(), OrderState.NEW);
            orderUpdatedToNewStateEventPort.publish(OrderUpdatedToNewStateEvent.from(order));
        } else {
            orderPort.updateState(order.getId(), OrderState.CANCELED);
        }
    }

    private Boolean checkAndUpdateStock(OrderItem item, Book book) {

        var isValid = book.getStock() > 0 && book.getStock() >= item.getQuantity();

        if (isValid) {
            book.setStock(book.getStock() - item.getQuantity());
        }

        return isValid;
    }
}
