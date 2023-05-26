package com.example.springbootlab.portal.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author artemis
 * @date 2023/5/26 15:45
 */
@RestController
public class HelloV2Controller {
    private static final Log logger = LogFactory.getLog(HelloV2Controller.class);


    @Value(value = "${spring.config.activate.on-profile}")
    private String profile;


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
