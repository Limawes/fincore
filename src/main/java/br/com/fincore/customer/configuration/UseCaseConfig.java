package br.com.fincore.customer.configuration;

import br.com.fincore.customer.application.CreateCustomerUseCase;
import br.com.fincore.customer.domain.port.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepositoryPort repositoryPort){
        return new CreateCustomerUseCase(repositoryPort);
    }
}
