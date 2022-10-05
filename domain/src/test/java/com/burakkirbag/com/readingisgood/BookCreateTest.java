package com.burakkirbag.com.readingisgood;

import com.burakkirbag.com.readingisgood.adapters.BookFakeDataAdapter;
import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import com.burakkirbag.readingisgood.book.usecase.BookCreateUseCaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BookCreateTest {

    BookCreateUseCaseHandler bookCreateUseCaseHandler;

    @BeforeEach
    void setUp() {
        bookCreateUseCaseHandler = new BookCreateUseCaseHandler(new BookFakeDataAdapter());
    }

    @Test
    void should_create_book() {
        // given
        BookCreate bookCreate = BookCreate.builder()
                .name("test book")
                .author("test author")
                .price(BigDecimal.valueOf(25))
                .stock(100)
                .build();

        // when
        var book = bookCreateUseCaseHandler.handle(bookCreate);

        // then
        assertThat(book).isNotNull()
                .returns("1", Book::getId)
                .returns("test book", Book::getName)
                .returns("test author", Book::getAuthor)
                .returns(BigDecimal.valueOf(25), Book::getPrice)
                .returns(100, Book::getStock);
    }
}
