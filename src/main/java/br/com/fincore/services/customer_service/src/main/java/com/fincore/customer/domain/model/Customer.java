package com.fincore.customer.domain.model;

import java.time.Instant;
import java.util.Objects;

public class Customer {

    private final CustomerId id;
    private final String cpfHash;
    private final String cpfLast4;
    private final String fullName;
    private String email;
    private CustomerStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Customer(CustomerId id, String cpfHash, String cpfLast4,
                    String fullName, String email, CustomerStatus status,
                    Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id, "CustomerId cannot be null");
        this.cpfHash = Objects.requireNonNull(cpfHash, "cpfHash cannot be null");
        this.cpfLast4 = Objects.requireNonNull(cpfLast4, "cpfLast4 cannot be null");
        this.fullName = Objects.requireNonNull(fullName, "fullName cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        this.status = Objects.requireNonNull(status, "status cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt cannot be null");
    }

    public static Customer create(CustomerId id, String cpfHash, String cpfLast4, String fullName, String email) {
        Instant now = Instant.now();
        return new Customer(id, cpfHash, cpfLast4, fullName, email, CustomerStatus.ACTIVE, now, now);
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void block() {
        this.status = CustomerStatus.BLOCKED;
        this.updatedAt = Instant.now();
    }

    public void changeEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail, "newEmail cannot be null");
        this.updatedAt = Instant.now();
    }
}
