package com.burakkirbag.readingisgood.adapters.book.rest.dto;

import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank(message = "Kitap adı girmelisiniz.")
    @Size(min = 1, max = 300, message = "Kitap adı en fazla 500 karakter olmalıdır.")
    private String name;

    @NotBlank(message = "Yazar girmelisiniz.")
    @Size(min = 1, max = 300, message = "Yazar en fazla 500 karakter olmalıdır.")
    private String author;

    @Min(value = 0, message = "Geçerli bir stok girmelisiniz.")
    private Integer stock;

    @Min(value = 1, message = "Geçerli bir fiyat girmelisiniz.")
    private BigDecimal price;

    public BookCreate toModel() {
        return BookCreate.builder()
                .name(name)
                .author(author)
                .stock(stock)
                .price(price)
                .build();
    }
}
