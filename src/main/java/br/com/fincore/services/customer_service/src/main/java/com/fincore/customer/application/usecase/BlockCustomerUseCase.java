package com.fincore.customer.application.usecase;

import com.fincore.customer.application.dto.CustomerResult;
import com.fincore.customer.domain.exception.CustomerNotFoundException;
import com.fincore.customer.domain.model.Customer;
import com.fincore.customer.domain.model.CustomerId;
import com.fincore.customer.domain.port.CustomerRepositoryPort;

import java.util.UUID;

public class BlockCustomerUseCase {

    private final CustomerRepositoryPort repositoryPort;

    public BlockCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public CustomerResult execute(UUID customerId) {
        Customer customer = repositoryPort.findById(new CustomerId(customerId))
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        customer.block();
        Customer saved = repositoryPort.save(customer);
        return CustomerResult.fromDomain(saved);
    }
}
