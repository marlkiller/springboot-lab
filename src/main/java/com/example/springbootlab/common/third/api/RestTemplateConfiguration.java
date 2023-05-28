package com.example.springbootlab.common.third.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Value(value = "${third.api}")
    private String thirdApi;

    @Value(value = "${third.apikey}")
    private String apikey;
    
    

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        // interceptors.add((request, body, execution) -> {
        //     request.getHeaders().set("interceptors_header", "interceptors_header_val");
        //     return execution.execute(request, body);
        // });
        return builder
                .defaultHeader("defaultHeader", apikey)
                .basicAuthentication("user", "pass")
                // .interceptors(interceptors)
                .build();
    }

}
