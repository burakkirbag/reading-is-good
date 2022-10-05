package com.burakkirbag.com.readingisgood;

import com.burakkirbag.com.readingisgood.adapters.BookFakeDataAdapter;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStockUseCaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookUpdateStockTest {
    BookUpdateStockUseCaseHandler bookUpdateStockUseCaseHandler;

    @BeforeEach
    void setUp() {
        bookUpdateStockUseCaseHandler = new BookUpdateStockUseCaseHandler(new BookFakeDataAdapter());
    }

    @Test
    void should_update_stock_book() {
        // given
        BookUpdateStock bookUpdateStock = BookUpdateStock.builder()
                .id("1")
                .stock(756)
                .build();

        var isUpdated = false;
        try {
            bookUpdateStockUseCaseHandler.handle(bookUpdateStock);
            isUpdated = true;
        } catch (Exception ex) {
        }

        assertTrue(isUpdated);
    }
}
