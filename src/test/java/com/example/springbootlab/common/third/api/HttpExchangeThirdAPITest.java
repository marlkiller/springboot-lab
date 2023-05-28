package com.example.springbootlab.common.third.api;

import com.example.springbootlab.SpringbootLabApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class HttpExchangeThirdAPITest extends SpringbootLabApplicationTests {


    @Resource
    private HttpExchangeThirdAPI httpExchangeThirdAPI;

    @Test
    void test() {
        httpExchangeThirdAPI.hello("val");
        httpExchangeThirdAPI.auth("auth_val");
        httpExchangeThirdAPI.auth2(Collections.singletonMap("map_k","map_v"),"auth2_val");
    }
}