package br.com.fincore.customer.domain.model;

import java.time.Instant;
import java.util.Objects;

public class Customer {

    private final CustomerId id;
    private final String cpfHash;
    private final String cpfLast4;
    private final String fullName;
    private String email;
    private CustomerStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public Customer(CustomerId id, String cpfHash, String cpfLast4, String fullName,
                    String email, CustomerStatus status, Instant createdAt,
                    Instant updatedAt) {
        this.id = id;
        this.cpfHash = cpfHash;
        this.cpfLast4 = cpfLast4;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Customer create(CustomerId id, String cpfHash, String cpfLast4,
                                  String fullName, String email){
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
        this.email = Objects.requireNonNull(newEmail, "New email cannot be null");
        this.updatedAt = Instant.now();
    }
}
