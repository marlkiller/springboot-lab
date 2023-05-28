package com.example.springbootlab.common.core;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
 
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("springboot-lab API")
                        .description("springboot-lab Restfull API")
                        .version("v0.0.1")
                        .termsOfService("https://voidm.com")
                        .license(new License().name("springboot-lab").url("https://voidm.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("springboot-lab Issues Documentation")
                        .url("https://voidm.com"));
    }
}
