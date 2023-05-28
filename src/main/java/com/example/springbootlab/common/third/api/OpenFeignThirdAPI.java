package com.example.springbootlab.common.third.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "OpenFeignThirdAPI", url = "${third.api}",configuration = FeignRequestInterceptor.class)
public interface OpenFeignThirdAPI {
    @GetMapping("/hello")
    String hello(@RequestParam String name);
    @PostMapping(value = "/auth", headers = {"Content-Type=application/json;charset=UTF-8", "auth=${third.apikey}"})
    String auth(@RequestParam String name);

    @GetMapping(value = "/auth2")
    String auth2(@RequestBody Map<String,String> map, @RequestHeader("auth2") String token);

}
