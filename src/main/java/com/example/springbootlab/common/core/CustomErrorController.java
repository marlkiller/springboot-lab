package com.example.springbootlab.common.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    private String path;

    public CustomErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的JSON响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        HttpStatus status = getStatus(request);
        Map<String, Object> originalMsgMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        originalMsgMap.put("custom_error_code_error", 10086);
        return new ResponseEntity<>(originalMsgMap, status);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), getErrorAttributes(request, ErrorAttributeOptions.defaults()));
        mav.addObject("custom_error_code_errorHtml", 10086);
        return mav;
    }
}