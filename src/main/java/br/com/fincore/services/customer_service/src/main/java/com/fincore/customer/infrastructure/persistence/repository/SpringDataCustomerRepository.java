package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.infrastructure.persistence.repository;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.infrastructure.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {
    boolean existsByCpfHash(String cpfHash);
    boolean existsByEmail(String email);
}
