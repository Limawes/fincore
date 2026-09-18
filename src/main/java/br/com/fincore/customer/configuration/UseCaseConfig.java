package br.com.fincore.customer.configuration;

import br.com.fincore.customer.application.usecase.CreateCustomerUseCase;
import br.com.fincore.customer.application.usecase.FindCustomerUseCase;
import br.com.fincore.customer.domain.port.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepositoryPort repositoryPort){
        return new CreateCustomerUseCase(repositoryPort);
    }

    @Bean
    public FindCustomerUseCase findCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        return new FindCustomerUseCase(repositoryPort);
    }
}
