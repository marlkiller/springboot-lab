package com.example.springbootlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootLabApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void test() {
        stringRedisTemplate.opsForValue().set("k_dev", "v_dev");
        System.out.println(stringRedisTemplate.opsForValue().get("k_dev"));
    }
}
