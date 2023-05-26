package com.example.springbootlab.portal.api;

import com.example.springbootlab.common.config.MyProperties;
import com.example.springbootlab.common.vo.Greeting;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author artemis
 * @date 2023/5/26 15:45
 */
@RestController
public class HelloController {

    @Resource
    private MyProperties myProperties;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * curl --location 'http://127.0.0.1:8080/greeting?name=123'
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        /* @GetMapping/@PostMapping/@PutMapping/@DeleteMapping
        这些注解都是@RequestMapping的简化版，用于标记GET/POST/PUT/DELETE请求映射。
        可以通过这些注解来更方便地定义不同类型的请求映射。 */
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * curl --location 'http://127.0.0.1:8080/greeting/v2/void'
     */
    @GetMapping("/greeting/v2/{name}")
    public Greeting greetingV2(@PathVariable(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // 后端已经使用了@RequestBody注解，代表只接收application/json类型的数据，
    // 此时若再传入application/x-www-form-urlencoded类型的数据，则后台会报错
    // @PostMapping(value = "/greeting/v3", headers = {"header_dev=header_val"})
    @PostMapping(value = "/greeting/v3")
    // @PostMapping(value = "/greeting/v3")
    public Map greetingV3(@RequestBody Map map) {
        // @RequestHeader Map<String, String> headers
        // @RequestHeader("user-id") String userId
        // @RequestHeader MultiValueMap<String, String> headers
        // @RequestHeader HttpHeaders headers
        // @CookieValue ...
        return map;
    }

    /**
     * consumes：指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
     * produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
     */
    @PostMapping(value = "/greeting/v4", consumes = {"text/*", MediaType.IMAGE_JPEG_VALUE})
    // @PostMapping(value = "/greeting/v4", produces = {"text/plain;charset=UTF-8"})
    // @PostMapping(value = "/greeting/v4")
    public Map greetingV4() {
        return new HashMap();
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        return "Greetings from Spring Boot! " + myProperties.getLevel();
    }

}
