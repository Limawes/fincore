package com.fincore.customer.application.usecase;

import com.fincore.customer.application.dto.CustomerResult;
import com.fincore.customer.domain.exception.CustomerNotFoundException;
import com.fincore.customer.domain.model.CustomerId;
import com.fincore.customer.domain.port.CustomerRepositoryPort;

import java.util.UUID;

public class FindCustomerUseCase {

    private final CustomerRepositoryPort repositoryPort;

    public FindCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public CustomerResult execute(UUID customerId) {
        return repositoryPort.findById(new CustomerId(customerId))
                .map(CustomerResult::fromDomain)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
