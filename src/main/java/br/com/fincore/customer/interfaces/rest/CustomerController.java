package br.com.fincore.customer.interfaces.rest;

import br.com.fincore.customer.application.CreateCustomerUseCase;
import br.com.fincore.customer.application.dto.CustomerResult;
import br.com.fincore.customer.interfaces.rest.request.CreateCustomerRequest;
import br.com.fincore.customer.interfaces.rest.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/customer/")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @PostMapping("v1.0/create")
    public ResponseEntity<CustomerResponse> create(Principal principal,
                                                   @Valid @RequestBody CreateCustomerRequest request) {
        CustomerResult result = createCustomerUseCase.execute(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.fromResult(result));
    }
}
