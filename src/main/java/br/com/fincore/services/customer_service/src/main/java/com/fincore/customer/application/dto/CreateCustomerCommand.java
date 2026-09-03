package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto;

public record CreateCustomerCommand(
        String rawCpf,
        String fullName,
        String email
) {
}
