package br.com.fincore.security.domain.request;

import br.com.fincore.security.domain.enumerator.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Email(message = "Email must be valid!")
        @NotNull
        String email,

        @NotNull
        @NotBlank
        @Size(min = 6, max = 15, message = "Password must be valid!")
        String password,

        @NotNull
        UserRole role) {
}
