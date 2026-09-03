package com.fincore.customer.application.dto;

public record CreateCustomerCommand(
        String rawCpf,
        String fullName,
        String email
) {
}
