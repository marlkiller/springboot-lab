package com.example.springbootlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringbootLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLabApplication.class, args);
    }
}
