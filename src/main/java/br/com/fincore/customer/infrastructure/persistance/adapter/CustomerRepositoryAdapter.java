package br.com.fincore.customer.infrastructure.persistance.adapter;

import br.com.fincore.customer.domain.port.CustomerRepositoryPort;
import br.com.fincore.customer.domain.model.Customer;
import br.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.customer.infrastructure.persistance.entity.CustomerJpaEntity;
import br.com.fincore.customer.infrastructure.persistance.mapper.CustomerPersistenceMapper;
import br.com.fincore.customer.infrastructure.persistance.repository.SpringDataCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerRepository repository;

    public CustomerRepositoryAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = CustomerPersistenceMapper.toEntity(customer);
        CustomerJpaEntity saved = repository.save(entity);
        return CustomerPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(CustomerId id) {
        return repository.findById(id.value())
                .map(CustomerPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByCpfHash(String cpfHash) {
        return repository.existsByCpfHash(cpfHash);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
