package com.example.springbootlab.common.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class GlobalInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        // TODO global auth
        // if ("anonymous".equals(request.getHeader("auth"))) {
        //     throw new MyException("global permissions error");
        // }
        // 返回 true 表示继续执行后续处理，返回 false 则终止请求处理
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 该方法在请求完成后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 记录请求完成时间，并计算请求耗时
        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        long duration = endTime - startTime;
        logger.info("Request completed. URL: {}, Time taken: {} ms", request.getRequestURI(), duration);
    }
}