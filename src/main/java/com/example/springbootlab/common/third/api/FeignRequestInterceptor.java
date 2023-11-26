// package com.example.springbootlab.common.third.api;
//
// import feign.RequestInterceptor;
// import feign.RequestTemplate;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpHeaders;
//
// @Configuration
// public class FeignRequestInterceptor implements RequestInterceptor {
//
//     @Override
//     public void apply(RequestTemplate template) {
//         template.header(HttpHeaders.AUTHORIZATION, "request_interceptor_token_val");
//     }
// }
