package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID value) {
    public CustomerId {
        Objects.requireNonNull(value, "CustomerId value cannot be null");
    }

    public static CustomerId newId() {
        return new CustomerId(UUID.randomUUID());
    }
}
