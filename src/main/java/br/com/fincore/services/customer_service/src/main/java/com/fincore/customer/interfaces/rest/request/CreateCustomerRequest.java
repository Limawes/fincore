package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.request;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CreateCustomerCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(
        @NotBlank(message = "CPF is required")
        String cpf,

        @NotBlank(message = "Full name is required")
        @Size(max = 160, message = "Full name must not exceed 160 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        String email
) {
    public CreateCustomerCommand toCommand() {
        return new CreateCustomerCommand(cpf, fullName, email);
    }
}
