package br.com.fincore.customer.application;

import br.com.fincore.CustomerRepositoryPort;
import br.com.fincore.customer.application.dto.CreateCustomerCommand;
import br.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.customer.domain.exception.DuplicateCustomerException;
import br.com.fincore.customer.domain.model.Cpf;
import br.com.fincore.customer.domain.model.Customer;
import br.com.fincore.customer.domain.model.CustomerId;

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
            throw new DuplicateCustomerException("Customer with given Email already exists");
        }

        Customer customer = Customer.create(
                CustomerId.newId(),
                cpfHash,
                cpfLast4,
                command.fullName(),
                command.email()
        );
        Customer newCustomer = repositoryPort.save(customer);
        return CustomerResult.fromDomain(newCustomer);
    }

}
