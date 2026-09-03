package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID id) {
        super("Customer not found with id: " + id);
    }
}
