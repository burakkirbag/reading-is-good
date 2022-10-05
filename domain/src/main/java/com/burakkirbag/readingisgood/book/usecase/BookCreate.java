package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookCreate implements UseCase {
    private String name;
    private String author;
    private Integer stock;
    private BigDecimal price;
}
