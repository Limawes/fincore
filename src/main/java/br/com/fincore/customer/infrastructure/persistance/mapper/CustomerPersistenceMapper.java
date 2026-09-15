package br.com.fincore.customer.infrastructure.persistance.mapper;

import br.com.fincore.customer.domain.model.Customer;
import br.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.customer.domain.model.CustomerStatus;
import br.com.fincore.customer.infrastructure.persistance.entity.CustomerJpaEntity;

public class CustomerPersistenceMapper {

    public static CustomerJpaEntity toEntity(Customer customer) {
        return new CustomerJpaEntity(
                customer.getId().value(),
                customer.getCpfHash(),
                customer.getCpfLast4(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getStatus().name(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                null
        );
    }


    public static Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(
                CustomerId.newId(),
                entity.getCpfHash(),
                entity.getCpfLast4(),
                entity.getFullName(),
                entity.getEmail(),
                CustomerStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
