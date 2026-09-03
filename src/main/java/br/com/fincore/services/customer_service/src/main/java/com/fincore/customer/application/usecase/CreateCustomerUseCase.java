package com.fincore.customer.application.usecase;

import com.fincore.customer.application.dto.CreateCustomerCommand;
import com.fincore.customer.application.dto.CustomerResult;
import com.fincore.customer.domain.exception.DuplicateCustomerException;
import com.fincore.customer.domain.model.Cpf;
import com.fincore.customer.domain.model.Customer;
import com.fincore.customer.domain.model.CustomerId;
import com.fincore.customer.domain.port.CustomerRepositoryPort;

public class CreateCustomerUseCase {

    private final CustomerRepositoryPort repositoryPort;

    public CreateCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public CustomerResult execute(CreateCustomerCommand command) {
        Cpf cpf = new Cpf(command.rawCpf());
        String cpfHash = cpf.getHash();
        String cpfLast4 = cpf.getLast4Digits();

        if (repositoryPort.existsByCpfHash(cpfHash)) {
            throw new DuplicateCustomerException("Customer with given CPF already exists");
        }

        if (repositoryPort.existsByEmail(command.email())) {
            throw new DuplicateCustomerException("Customer with given email already exists");
        }

        Customer newCustomer = Customer.create(
                CustomerId.newId(),
                cpfHash,
                cpfLast4,
                command.fullName(),
                command.email()
        );

        Customer saved = repositoryPort.save(newCustomer);
        return CustomerResult.fromDomain(saved);
    }
}
