package com.fincore.customer.domain.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException() {
        super("Cpf is invalid!");
    }
}
