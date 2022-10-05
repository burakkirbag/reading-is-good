package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRetrieve implements UseCase {

    private String id;

}
