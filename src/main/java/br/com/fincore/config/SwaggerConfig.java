package br.com.fincore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.fincore.version}")
    private String version;

    @Value("${spring.application.fincore.description}")
    private String projectDescription;

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("fincore-api-1.0")
                .packagesToScan("br.com.fincore.customer.interfaces.rest.controller",
                        "br.com.fincore.security.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo(){
        String description = "Documentation for FINCORE API v1.0 \n\n %s \n\n Developed by: Wesley Lima ";
        return new Info()
                .title("Fincore - Distributed Financial Services Platform")
                .version(version)
                .description(String.format(description, projectDescription));
    }

}
