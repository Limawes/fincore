package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer.application.usecase;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CreateCustomerCommand;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.exception.DuplicateCustomerException;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.Customer;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerStatus;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.port.CustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateCustomerUseCaseTest {

    private CustomerRepositoryPort repositoryPort;
    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        repositoryPort = Mockito.mock(CustomerRepositoryPort.class);
        useCase = new CreateCustomerUseCase(repositoryPort);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        CreateCustomerCommand command = new CreateCustomerCommand("52998224725", "Jane Doe", "jane@example.com");

        when(repositoryPort.existsByCpfHash(any())).thenReturn(false);
        when(repositoryPort.existsByEmail("jane@example.com")).thenReturn(false);
        when(repositoryPort.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResult result = useCase.execute(command);

        assertNotNull(result.id());
        assertEquals("Jane Doe", result.fullName());
        assertEquals("jane@example.com", result.email());
        assertEquals(CustomerStatus.ACTIVE, result.status());
    }

    @Test
    void shouldRejectDuplicateCpf() {
        CreateCustomerCommand command = new CreateCustomerCommand("52998224725", "Jane Doe", "jane@example.com");

        when(repositoryPort.existsByCpfHash(any())).thenReturn(true);

        assertThrows(DuplicateCustomerException.class, () -> useCase.execute(command));
    }
}
