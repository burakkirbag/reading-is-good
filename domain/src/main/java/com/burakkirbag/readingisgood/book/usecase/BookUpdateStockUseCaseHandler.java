package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class BookUpdateStockUseCaseHandler implements VoidUseCaseHandler<BookUpdateStock> {

    private final BookPort bookPort;

    @Override
    public void handle(BookUpdateStock useCase) {
        try {
            bookPort.updateStock(useCase);
        } catch (Exception e) {
            throw new BusinessException("bookapi.book.couldNotStockUpdate");
        }
    }
}
