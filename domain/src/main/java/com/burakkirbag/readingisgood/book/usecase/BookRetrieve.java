package com.burakkirbag.readingisgood.book.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRetrieve implements UseCase {
    private String id;
}
