package com.fincore.customer.infrastructure.persistence.repository;

import com.fincore.customer.infrastructure.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {
    boolean existsByCpfHash(String cpfHash);
    boolean existsByEmail(String email);
}
