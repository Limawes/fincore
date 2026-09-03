package com.fincore.customer.interfaces.rest.response;

import com.fincore.customer.application.dto.CustomerResult;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String fullName,
        String email,
        String status,
        Instant createdAt
) {
    public static CustomerResponse fromResult(CustomerResult result) {
        return new CustomerResponse(
                result.id(),
                result.fullName(),
                result.email(),
                result.status().name(),
                result.createdAt()
        );
    }
}
