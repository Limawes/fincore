package com.fincore.customer.configuration;

import com.fincore.customer.application.usecase.BlockCustomerUseCase;
import com.fincore.customer.application.usecase.CreateCustomerUseCase;
import com.fincore.customer.application.usecase.FindCustomerUseCase;
import com.fincore.customer.domain.port.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        return new CreateCustomerUseCase(repositoryPort);
    }

    @Bean
    public FindCustomerUseCase findCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        return new FindCustomerUseCase(repositoryPort);
    }

    @Bean
    public BlockCustomerUseCase blockCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        return new BlockCustomerUseCase(repositoryPort);
    }
}
