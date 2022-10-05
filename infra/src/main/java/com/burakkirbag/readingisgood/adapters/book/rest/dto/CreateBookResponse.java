package com.burakkirbag.readingisgood.adapters.book.rest.dto;

import com.burakkirbag.readingisgood.book.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookResponse {

    private String id;

    private String name;

    private String author;

    private Integer stock;

    private BigDecimal price;

    public static CreateBookResponse fromModel(Book book) {
        return CreateBookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .stock(book.getStock())
                .price(book.getPrice())
                .build();
    }
}
