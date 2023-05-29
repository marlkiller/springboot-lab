package com.example.springbootlab.common.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**"); // 指定拦截器要拦截的路径
                registry.addInterceptor(new UserAPIInterceptor()).addPathPatterns("/user-api/**"); // 指定拦截器要拦截的路径
            }
        };
    }
}