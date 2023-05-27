package com.example.springbootlab.common.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = {"com"})
public class ControllerExceptionHandler {
    record ErrorMessage(int statusCode, LocalDateTime localDateTime, String message, String description) {

    }

    @ExceptionHandler(value = {Exception.class})
    // 没有这个注解的话,会走 dispatch 的 error
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exception(Exception ex, WebRequest request) {

        return new ErrorMessage(
                520,
                LocalDateTime.now(),
                ex.getMessage(),
                "this is description");
    }

    @ExceptionHandler(value = {MyException.class})
    // If you use @ControllerAdvice without @ResponseBody and @ResponseStatus, you can return ResponseEntity object instead.
    // return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage myException(MyException ex, WebRequest request) {

        return new ErrorMessage(
                420,
                LocalDateTime.now(),
                ex.getMessage(),
                "this is description");
    }
}