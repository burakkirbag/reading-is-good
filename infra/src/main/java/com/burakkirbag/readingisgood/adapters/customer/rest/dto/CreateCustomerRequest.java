package com.burakkirbag.readingisgood.adapters.customer.rest.dto;

import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotBlank(message = "Müşteri adı girmelisiniz.")
    @Size(min = 2, max = 50, message = "Müşteri adı, en az 2, en fazla 50 karakter olmalıdır.")
    private String firstName;

    @NotBlank(message = "Müşteri soyadı girmelisiniz.")
    @Size(min = 2, max = 50, message = "Müşteri soyadı, en az 2, en fazla 50 karakter olmalıdır.")
    private String lastName;

    @Email(message = "Geçerli bir e-posta adresi girmelisiniz.")
    private String email;

    @NotNull(message = "Şifre, 4 ila 15 karakter arasında olmalıdır.")
    @Size(min = 4, max = 15, message = "Şifre, 4 ila 15 karakter arasında olmalıdır.")
    private String password;

    public CustomerCreate toModel() {
        return CustomerCreate.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
    }
}
