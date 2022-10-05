package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class BookCreateUseCaseHandler implements UseCaseHandler<Book, BookCreate> {

    private final BookPort bookPort;

    @Override
    public Book handle(BookCreate useCase) {
        var book = bookPort.create(useCase);
        return book;
    }
}
