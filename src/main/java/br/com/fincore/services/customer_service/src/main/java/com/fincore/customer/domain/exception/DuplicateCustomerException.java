package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String message) {
        super(message);
    }
}
