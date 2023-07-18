package com.example.springbootlab.portal.api;

import com.example.springbootlab.common.config.MyProperties;
import com.example.springbootlab.common.constant.ResultCode;
import com.example.springbootlab.common.dto.PageForm;
import com.example.springbootlab.common.util.JSONConvertUtils;
import com.example.springbootlab.common.vo.Greeting;
import com.example.springbootlab.common.vo.PageVo;
import com.example.springbootlab.common.vo.ResultVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/**
 * @author artemis
 * @date 2023/5/26 15:45
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private MyProperties myProperties;

    @Value(value = "${spring.config.activate.on-profile}")
    private String profile;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/num")
    public void num(String num) throws InterruptedException {
        // num/counter :http-nio-8080-exec-200 : 200/200 
        logger.info("thread : num/counter :{} : {}/{}", Thread.currentThread().getName(), num, counter.incrementAndGet());
        TimeUnit.HOURS.sleep(1);
    }
    

    /**
     * curl --location 'http://127.0.0.1:8080/greeting?name=123'
     */
    @GetMapping("/env")
    public String env() {
        /* @GetMapping/@PostMapping/@PutMapping/@DeleteMapping
        这些注解都是@RequestMapping的简化版，用于标记GET/POST/PUT/DELETE请求映射。
        可以通过这些注解来更方便地定义不同类型的请求映射。 */
        if (logger.isDebugEnabled()) {
            logger.debug("this is debug log");
            // 不过还有最好的写法，使用占位符：
            // Object entry = new SomeObject(); 
            // logger.debug("The entry is {}.", entry);
        }

        logger.info("this is info log");
        logger.warn("this is warn log");
        logger.error("this is error log");
        return profile;
    }

    @GetMapping("/cookie/req1")
    public Map cookieReq1(HttpServletResponse response) {

        Cookie cookie = new Cookie("cookie_k", "val_1");
        Cookie cookie2 = new Cookie("cookie_k2", "val_2");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        return Collections.singletonMap("cookie_k", "cookie_k2");
    }

    @GetMapping("/cookie/req2")
    public Map cookieReq2(@CookieValue(value = "cookie_k") String val, @CookieValue(value = "cookie_k2") String val2) {
        return Collections.singletonMap(val, val2);
    }

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
    // @PostMapping(value = "/greeting/v4", consumes = {"text/*", MediaType.IMAGE_JPEG_VALUE})
    // @PostMapping(value = "/greeting/v4", produces = {"text/plain;charset=UTF-8"})
    @PostMapping(value = "/greeting/v4")
    public String greetingV4() {
        String jsonString = """
                {
                    "id":123,
                    "content":"content_val"
                }
                """;

        Greeting jsonObject = JSONConvertUtils.asJSONObject(jsonString, Greeting.class);
        return JSONConvertUtils.asString(Collections.singletonMap("key", "val"));
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        return "Greetings from Spring Boot! " + myProperties.getLevel();
    }

    @GetMapping("/result_vo/success")
    public ResultVo<Object> success() {
        return ResultVo.ok();
    }

    @GetMapping("/result_vo/fail")
    public ResultVo<Object> fail() {
        return ResultVo.fail(ResultCode.RC500);
    }

    @GetMapping("/page")
    public ResultVo<Object> page() {

        PageForm pageForm = new PageForm();
        pageForm.setPageNumber(2);
        pageForm.setPageSize(100);


        // mysql 查询中,会用到 currentPage
        // pageForm.calcCurrent()

        PageVo<Greeting> pageVo = PageVo.of(
                pageForm,
                Arrays.asList(
                        new Greeting(1, "id_1"),
                        new Greeting(2, "id_2")
                ),
                1000

        );

        // return ResultVo.ok(pageVo);
        // 这里可以抽取一个 Convert 方法, 过滤某些关键字
        return ResultVo.ok(pageVo.convert(new Function<Greeting, Greeting>() {
            @Override
            public Greeting apply(Greeting greeting) {
                if (greeting.id() == 1) {
                    return new Greeting(1000, "mask");
                }
                return greeting;
            }
        }));
    }
}
