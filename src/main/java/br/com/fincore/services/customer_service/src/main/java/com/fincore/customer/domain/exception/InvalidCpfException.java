package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException() {
        super("Cpf is invalid!");
    }
}
