package com.example.springbootlab.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController()
@RequestMapping("/user-api")
public class UserAPIController {

    /**
     * curl --location 'http://127.0.0.1:8080/user-api/test'
     */
    @RequestMapping("test")
    public Map<String, String> greeting() {
        return Collections.singletonMap("hello", "user");
    }
}
