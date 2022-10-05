package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class BookGetAllUseCaseHandler implements UseCaseHandler<PagedList<Book>, BookGetAll> {

    private final BookPort bookPort;

    @Override
    public PagedList<Book> handle(BookGetAll useCase) {
        return bookPort.getAll(useCase);
    }
}
