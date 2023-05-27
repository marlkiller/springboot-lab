package com.example.springbootlab.portal.api;

import com.example.springbootlab.common.core.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author artemis
 * @date 2023/5/26 15:45
 */
@RestController
public class HelloV2Controller {
    private static final Logger logger = LoggerFactory.getLogger(HelloV2Controller.class);


    @Value(value = "${spring.config.activate.on-profile}")
    private String profile;

    @GetMapping("/hello/ex/{type}")
    public Object helloEx(@PathVariable(value = "type") String type) throws Exception {
        switch (type) {
            case "1" -> throw new Exception("Exception");
            case "2" -> throw new MyException("MyException");
        }
        return new Object();
    }


    @GetMapping("/env/v2")
    public String env() {
        if (logger.isDebugEnabled()) {
            logger.debug("this is debug log");
        }
        logger.info("this is info log");
        logger.warn("this is warn log");
        logger.error("this is error log");
        return profile;
    }

}
