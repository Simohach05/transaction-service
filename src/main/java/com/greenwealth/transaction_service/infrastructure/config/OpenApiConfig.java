package com.greenwealth.transaction_service.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI greenWealthOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("GreenWealth IA - Transaction Service")
                        .description("API for transactions management and automatic services.")
                        .version("1.0.0"));

    }
}
