package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookGetAll implements UseCase {
    private int page;
    private int size;
}
