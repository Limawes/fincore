package br.com.fincore.customer.domain.port;

import br.com.fincore.customer.domain.model.Customer;
import br.com.fincore.customer.domain.model.CustomerId;

import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId id);
    boolean existsByCpfHash(String cpfHash);
    boolean existsByEmail(String email);
}
