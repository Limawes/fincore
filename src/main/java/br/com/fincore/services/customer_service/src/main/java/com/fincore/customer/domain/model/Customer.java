package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final CustomerId id;
    private final String cpfHash;
    private final String cpfLast4;
    private final String fullName;
    private String email;
    private CustomerStatus status;

    public Customer(CustomerId id, String cpfHash, String cpfLast4,
                    String fullName, String email, CustomerStatus status,
                    Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id);
        this.cpfHash = Objects.requireNonNull(cpfHash);
        this.cpfLast4 = Objects.requireNonNull(cpfLast4);
        this.fullName = Objects.requireNonNull(fullName);
        this.email = Objects.requireNonNull(email);
        this.status = Objects.requireNonNull(status);
    }

    public CustomerId getId() {
        return id;
    }

    public String getCpfHash() {
        return cpfHash;
    }

    public String getCpfLast4() {
        return cpfLast4;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void block() {
        this.status = CustomerStatus.BLOCKED;
    }

    public void changeEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail);
    }
}
