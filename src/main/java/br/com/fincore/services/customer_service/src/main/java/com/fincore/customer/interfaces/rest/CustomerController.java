package com.fincore.customer.interfaces.rest;

import com.fincore.customer.application.dto.CustomerResult;
import com.fincore.customer.application.usecase.BlockCustomerUseCase;
import com.fincore.customer.application.usecase.CreateCustomerUseCase;
import com.fincore.customer.application.usecase.FindCustomerUseCase;
import com.fincore.customer.interfaces.rest.request.CreateCustomerRequest;
import com.fincore.customer.interfaces.rest.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final BlockCustomerUseCase blockCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
                              FindCustomerUseCase findCustomerUseCase,
                              BlockCustomerUseCase blockCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerUseCase = findCustomerUseCase;
        this.blockCustomerUseCase = blockCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CustomerResult result = createCustomerUseCase.execute(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.fromResult(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID id) {
        CustomerResult result = findCustomerUseCase.execute(id);
        return ResponseEntity.ok(CustomerResponse.fromResult(result));
    }

    @PostMapping("/{id}/block")
    public ResponseEntity<CustomerResponse> blockCustomer(@PathVariable UUID id) {
        CustomerResult result = blockCustomerUseCase.execute(id);
        return ResponseEntity.ok(CustomerResponse.fromResult(result));
    }
}
