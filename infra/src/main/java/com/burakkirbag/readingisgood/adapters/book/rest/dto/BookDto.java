package com.burakkirbag.readingisgood.adapters.book.rest.dto;

import com.burakkirbag.readingisgood.book.model.Book;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookDto {

    private String id;

    private String name;

    private String author;

    private Integer stock;

    private BigDecimal price;

    public static BookDto fromModel(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .stock(book.getStock())
                .price(book.getPrice())
                .build();
    }
}
