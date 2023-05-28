package com.example.springbootlab.common.third.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpExchangeConfiguration {
    @Value(value = "${third.api}")
    private String thirdApi;
    
    @Value(value = "${third.apikey}")
    private String apikey;
    @Bean
    HttpExchangeThirdAPI httpExchangeThirdAPI() {
        WebClient client = WebClient.builder().defaultHeader("defaultHeader",apikey).baseUrl(thirdApi).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(HttpExchangeThirdAPI.class);
    }

}
