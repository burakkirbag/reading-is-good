package com.burakkirbag.readingisgood.book.port;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import com.burakkirbag.readingisgood.book.usecase.BookGetAll;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import com.burakkirbag.readingisgood.common.model.PagedList;

import java.util.List;

public interface BookPort {

    Book retrieve(String id);

    List<Book> retrieve(List<String> ids);

    PagedList<Book> getAll(BookGetAll bookGetAll);

    Book create(BookCreate bookCreate);

    Book updateStock(BookUpdateStock bookUpdateStock);

    List<Book> updateStock(List<Book> books);
}
