package com.burakkirbag.readingisgood.book.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Book {

    private String id;
    private String name;
    private String author;
    private Integer stock;
    private BigDecimal price;
}
