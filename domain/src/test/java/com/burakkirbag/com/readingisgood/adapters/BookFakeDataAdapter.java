package com.burakkirbag.com.readingisgood.adapters;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import com.burakkirbag.readingisgood.book.usecase.BookGetAll;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import com.burakkirbag.readingisgood.common.model.PagedList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BookFakeDataAdapter implements BookPort {
    @Override
    public Book retrieve(String id) {

        if (id == "9999") return null;

        return Book.builder()
                .id(id)
                .name("test book")
                .author("test author")
                .stock(100)
                .price(BigDecimal.valueOf(25))
                .build();
    }

    @Override
    public List<Book> retrieve(List<String> ids) {
        var books = new ArrayList<Book>();
        ids.forEach(id -> {
            books.add(
                    Book.builder()
                            .id(id)
                            .name("test book " + id)
                            .author("test author " + id)
                            .stock(ThreadLocalRandom.current().nextInt())
                            .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt()))
                            .build()
            );
        });
        return books;
    }

    @Override
    public PagedList<Book> getAll(BookGetAll bookGetAll) {
        var books = List.of(
                Book.builder()
                        .id("1")
                        .name("test book 1")
                        .author("test author 1")
                        .stock(100)
                        .price(BigDecimal.valueOf(25))
                        .build(),
                Book.builder()
                        .id("2")
                        .name("test book 2")
                        .author("test author 2")
                        .stock(100)
                        .price(BigDecimal.valueOf(35))
                        .build(),
                Book.builder()
                        .id("3")
                        .name("test book 3")
                        .author("test author 3")
                        .stock(100)
                        .price(BigDecimal.valueOf(45))
                        .build()
        );

        return new PagedList<Book>(books, 1, 5, books.stream().count());
    }

    @Override
    public Book create(BookCreate bookCreate) {
        return Book.builder()
                .id("1")
                .name("test book")
                .author("test author")
                .stock(100)
                .price(BigDecimal.valueOf(25))
                .build();
    }

    @Override
    public Book updateStock(BookUpdateStock bookUpdateStock) {
        return Book.builder()
                .id("1")
                .name("test book")
                .author("test author")
                .stock(100)
                .price(BigDecimal.valueOf(25))
                .build();
    }

    @Override
    public List<Book> updateStock(List<Book> books) {
        return books;
    }
}
