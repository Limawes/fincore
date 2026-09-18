package br.com.fincore.customer.interfaces.rest.controller;

import br.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.customer.application.usecase.FindCustomerUseCase;
import br.com.fincore.customer.domain.exception.CustomerNotFoundException;
import br.com.fincore.customer.domain.model.CustomerId;
import br.com.fincore.customer.interfaces.rest.request.CreateCustomerRequest;
import br.com.fincore.customer.interfaces.rest.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, FindCustomerUseCase findCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerUseCase = findCustomerUseCase;
    }

    @PostMapping("/v1.0/create")
    public ResponseEntity<CustomerResponse> create(Principal principal,
                                                   @Valid @RequestBody CreateCustomerRequest request) {
        CustomerResult result = createCustomerUseCase.execute(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.fromResult(result));
    }

    @GetMapping("/v1.0/{id}")
    public ResponseEntity<CustomerResult> getCustomer(Principal principal,
                                                   @Valid @PathVariable UUID id) {
        try {
            CustomerResult result = findCustomerUseCase.findById(new CustomerId(id));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (CustomerNotFoundException ce) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
