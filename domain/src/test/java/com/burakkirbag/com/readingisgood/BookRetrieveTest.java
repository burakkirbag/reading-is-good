package com.burakkirbag.com.readingisgood;

import com.burakkirbag.com.readingisgood.adapters.BookFakeDataAdapter;
import com.burakkirbag.readingisgood.book.usecase.BookRetrieve;
import com.burakkirbag.readingisgood.book.usecase.BookRetrieveUseCaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BookRetrieveTest {
    BookRetrieveUseCaseHandler bookRetrieveUseCaseHandler;

    @BeforeEach
    void setUp() {
        bookRetrieveUseCaseHandler = new BookRetrieveUseCaseHandler(new BookFakeDataAdapter());
    }

    @Test
    void should_retrieve_book() {
        // given
        BookRetrieve bookRetrieve = BookRetrieve.builder()
                .id("1")
                .build();

        // when
        var book = bookRetrieveUseCaseHandler.handle(bookRetrieve);

        // then
        assertThat(book).isNotNull();
    }

    @Test
    void should_retrieve_no_book() {
        // given
        BookRetrieve bookRetrieve = BookRetrieve.builder()
                .id("9999")
                .build();

        // when
        var book = bookRetrieveUseCaseHandler.handle(bookRetrieve);

        // then
        assertThat(book).isNull();
    }
}
