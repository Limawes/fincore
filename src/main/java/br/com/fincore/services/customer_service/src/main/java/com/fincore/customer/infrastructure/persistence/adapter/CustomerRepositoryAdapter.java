package com.fincore.customer.infrastructure.persistence.adapter;

import com.fincore.customer.domain.model.Customer;
import com.fincore.customer.domain.model.CustomerId;
import com.fincore.customer.domain.port.CustomerRepositoryPort;
import com.fincore.customer.infrastructure.persistence.entity.CustomerJpaEntity;
import com.fincore.customer.infrastructure.persistence.mapper.CustomerPersistenceMapper;
import com.fincore.customer.infrastructure.persistence.repository.SpringDataCustomerRepository;
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
        CustomerJpaEntity entity = CustomerPersistenceMapper.toJpaEntity(customer);
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
