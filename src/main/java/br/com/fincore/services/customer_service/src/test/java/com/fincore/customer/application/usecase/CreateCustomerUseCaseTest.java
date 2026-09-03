package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer.application.usecase;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CreateCustomerCommand;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.DuplicateCustomerException;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.Customer;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerStatus;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.port.CustomerRepositoryPort;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class CreateCustomerUseCaseTest {

    @Test
    public void shouldCreateCustomerSuccessfully() {
        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        CreateCustomerUseCase useCase = new CreateCustomerUseCase(repository);
        CreateCustomerCommand command = new CreateCustomerCommand("52998224725", "Jane Doe", "jane@example.com");

        CustomerResult result = useCase.execute(command);

        assertNotNull(result.id());
        assertEquals("Jane Doe", result.fullName());
        assertEquals("jane@example.com", result.email());
        assertEquals(CustomerStatus.ACTIVE, result.status());
        assertNotNull(repository.savedCustomer);
    }

    @Test
    public void shouldRejectDuplicateCpf() {
        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        repository.cpfAlreadyExists = true;
        CreateCustomerUseCase useCase = new CreateCustomerUseCase(repository);
        CreateCustomerCommand command = new CreateCustomerCommand("52998224725", "Jane Doe", "jane@example.com");

        assertThrows(DuplicateCustomerException.class, () -> useCase.execute(command));
        assertNull(repository.savedCustomer);
    }

    @Test
    public void shouldRejectDuplicateEmail() {
        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        repository.emailAlreadyExists = true;
        CreateCustomerUseCase useCase = new CreateCustomerUseCase(repository);
        CreateCustomerCommand command = new CreateCustomerCommand("52998224725", "Jane Doe", "jane@example.com");

        assertThrows(DuplicateCustomerException.class, () -> useCase.execute(command));
        assertNull(repository.savedCustomer);
    }

    private static final class InMemoryCustomerRepository implements CustomerRepositoryPort {
        private boolean cpfAlreadyExists;
        private boolean emailAlreadyExists;
        private Customer savedCustomer;

        @Override
        public Customer save(Customer customer) {
            savedCustomer = customer;
            return customer;
        }

        @Override
        public Optional<Customer> findById(CustomerId id) {
            return Optional.empty();
        }

        @Override
        public boolean existsByCpfHash(String cpfHash) {
            return cpfAlreadyExists;
        }

        @Override
        public boolean existsByEmail(String email) {
            return emailAlreadyExists;
        }
    }
}
