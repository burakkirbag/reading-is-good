package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookUpdateStock implements UseCase {
    private String id;
    private Integer stock;
}
