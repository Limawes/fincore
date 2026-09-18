package br.com.fincore.customer.application.usecase;

import br.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.customer.domain.exception.CustomerNotFoundException;
import br.com.fincore.customer.domain.model.Customer;
import br.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.customer.domain.port.CustomerRepositoryPort;

import java.util.Optional;

public class FindCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public FindCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public CustomerResult findById (CustomerId customerId) {
        Optional<Customer> optionalCustomer = customerRepositoryPort.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("User wasn't found!");
        }
        var customer = optionalCustomer.get();
        return CustomerResult.fromDomain(customer);
    }
}
