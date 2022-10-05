package com.burakkirbag.readingisgood.adapters.book.rest.dto;

import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequest {

    @Min(value = 0, message = "Geçerli bir stok girmelisiniz.")
    private Integer stock;

    public BookUpdateStock toModel(String id) {
        return BookUpdateStock.builder()
                .id(id)
                .stock(stock)
                .build();
    }

}
