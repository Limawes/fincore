package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.Customer;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerStatus;

import java.time.Instant;
import java.util.UUID;

public record CustomerResult(
        UUID id,
        String fullName,
        String email,
        CustomerStatus status,
        Instant createdAt,
        Instant updatedAt
) {
    public static CustomerResult fromDomain(Customer customer) {
        return new CustomerResult(
                customer.getId().value(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
