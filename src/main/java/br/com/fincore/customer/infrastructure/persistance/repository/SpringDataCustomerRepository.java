package br.com.fincore.customer.infrastructure.persistance.repository;

import br.com.fincore.customer.infrastructure.persistance.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {
    boolean existsByCpfHash(String hash);
    boolean existsByEmail(String email);
}
