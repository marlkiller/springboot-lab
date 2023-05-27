package com.example.springbootlab.common.core;

public class MyException extends Exception {
    private String msg;

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
