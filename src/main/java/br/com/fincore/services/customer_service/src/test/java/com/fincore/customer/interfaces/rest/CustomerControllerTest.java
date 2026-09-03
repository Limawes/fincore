package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer.interfaces.rest;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.BlockCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.FindCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.Customer;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.port.CustomerRepositoryPort;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.CustomerController;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.request.CreateCustomerRequest;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.response.CustomerResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerControllerTest {

    @Test
    public void shouldCreateCustomerWithoutExposingCpf() {
        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        CustomerController controller = new CustomerController(
                new CreateCustomerUseCase(repository),
                new FindCustomerUseCase(repository),
                new BlockCustomerUseCase(repository)
        );
        CreateCustomerRequest request = new CreateCustomerRequest(
                "52998224725", "John Doe", "john@example.com"
        );

        ResponseEntity<CustomerResponse> response = controller.createCustomer(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id());
        assertEquals("John Doe", response.getBody().fullName());
        assertEquals("john@example.com", response.getBody().email());
        assertEquals("ACTIVE", response.getBody().status());
    }

    private static final class InMemoryCustomerRepository implements CustomerRepositoryPort {
        private Customer customer;

        @Override
        public Customer save(Customer customer) {
            this.customer = customer;
            return customer;
        }

        @Override
        public Optional<Customer> findById(CustomerId id) {
            return Optional.ofNullable(customer)
                    .filter(savedCustomer -> savedCustomer.getId().equals(id));
        }

        @Override
        public boolean existsByCpfHash(String cpfHash) {
            return customer != null && customer.getCpfHash().equals(cpfHash);
        }

        @Override
        public boolean existsByEmail(String email) {
            return customer != null && customer.getEmail().equals(email);
        }
    }
}
