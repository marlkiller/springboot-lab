package com.example.springbootlab.common.third.api;

import com.example.springbootlab.SpringbootLabApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class OpenFeignThirdAPITest extends SpringbootLabApplicationTests {
    @Resource
    private OpenFeignThirdAPI openFeignThirdAPI;
    @Test
    void test() {
        openFeignThirdAPI.hello("val");
        openFeignThirdAPI.auth("auth_val");
        openFeignThirdAPI.auth2(Collections.singletonMap("map_k","map_v"),"auth2_val");
    }
}