package com.burakkirbag.readingisgood.adapters.authentication.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "Geçerli bir e-posta adresi girmelisiniz.")
    @NotEmpty(message = "Geçerli bir e-posta adresi girmelisiniz.")
    @Email(message = "Geçerli bir e-posta adresi girmelisiniz.")
    private String username;

    @NotNull(message = "Şifre, 4 ila 15 karakter arasında olmalıdır.")
    @Size(min = 4, max = 15, message = "Şifre, 4 ila 15 karakter arasında olmalıdır.")
    private String password;
}
