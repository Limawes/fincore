package com.fincore.customer.infrastructure.persistence.mapper;

import com.fincore.customer.domain.model.Customer;
import com.fincore.customer.domain.model.CustomerId;
import com.fincore.customer.domain.model.CustomerStatus;
import com.fincore.customer.infrastructure.persistence.entity.CustomerJpaEntity;

public class CustomerPersistenceMapper {

    public static CustomerJpaEntity toJpaEntity(Customer customer) {
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
                new CustomerId(entity.getId()),
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
