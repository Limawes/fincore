package br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.CustomerNotFoundException;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.port.CustomerRepositoryPort;

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
