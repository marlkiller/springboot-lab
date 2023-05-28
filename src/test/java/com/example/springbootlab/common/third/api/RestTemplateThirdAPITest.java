package com.example.springbootlab.common.third.api;

import com.example.springbootlab.SpringbootLabApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

class RestTemplateThirdAPITest extends SpringbootLabApplicationTests {
    @Resource
    private RestTemplate restTemplate;

    @Value(value = "${third.api}")
    private String thirdApi;

    @Value(value = "${third.apikey}")
    private String apikey;

    @Test
    void test() {
        System.out.println(restTemplate.getForEntity(thirdApi, String.class, new HashMap<>()));


        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set custom header
        headers.set("auth", "auth_val");
        HttpEntity<String> request = new HttpEntity <>(headers);
        System.out.println(restTemplate.exchange(thirdApi, HttpMethod.GET, request, String.class));

    }
}