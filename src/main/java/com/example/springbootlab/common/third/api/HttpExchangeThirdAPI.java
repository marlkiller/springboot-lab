package com.example.springbootlab.common.third.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@HttpExchange()
public interface HttpExchangeThirdAPI {
    @GetExchange("/hello")
    String hello(@RequestParam String name);
    @PostExchange(value = "/auth")
    String auth(@RequestParam String name);

    @GetExchange(value = "/auth2")
    String auth2(@RequestBody Map<String,String> map, @RequestHeader("auth2") String token);

}
