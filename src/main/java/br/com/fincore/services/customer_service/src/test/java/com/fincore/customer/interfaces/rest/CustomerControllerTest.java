package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer.interfaces.rest;

import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.BlockCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.application.usecase.FindCustomerUseCase;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.domain.model.CustomerStatus;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.CustomerController;
import br.com.fincore.services.customer_service.src.main.java.com.fincore.customer.interfaces.rest.error.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes = {CustomerController.class, GlobalExceptionHandler.class})
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCustomerUseCase createCustomerUseCase;

    @MockBean
    private FindCustomerUseCase findCustomerUseCase;

    @MockBean
    private BlockCustomerUseCase blockCustomerUseCase;

    @Test
    void shouldCreateCustomerAndNotExposeRawCpfInResponse() throws Exception {
        UUID customerId = UUID.randomUUID();
        CustomerResult result = new CustomerResult(
                customerId, "John Doe", "john@example.com", CustomerStatus.ACTIVE, Instant.now(), Instant.now()
        );

        when(createCustomerUseCase.execute(any())).thenReturn(result);

        String rawCpf = "52998224725";
        String requestBody = """
                {
                    "cpf": "%s",
                    "fullName": "John Doe",
                    "email": "john@example.com"
                }
                """.formatted(rawCpf);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.fullName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$").value(not(containsString(rawCpf))));
    }
}
