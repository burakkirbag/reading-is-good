package com.burakkirbag.readingisgood.adapters.book.mongodb.entity;

import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document("books")
public class BookEntity extends AbstractEntity {

    private String name;

    private String author;

    private Integer stock;

    private BigDecimal price;

    public Book toModel() {
        return Book.builder()
                .id(super.getId())
                .name(name)
                .author(author)
                .stock(stock)
                .price(price)
                .build();
    }
}
