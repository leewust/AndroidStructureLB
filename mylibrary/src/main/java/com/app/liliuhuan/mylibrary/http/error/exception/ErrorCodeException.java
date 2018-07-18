package com.app.liliuhuan.mylibrary.http.error.exception;

import java.io.IOException;

/**
 * @Description: 自定义出来服务器返回的错误消息
 * @Author 杨建波
 * @Date 2018/1/25 10:34
 * @Email Android_panpan@163.com
 */
public class ErrorCodeException extends IOException {

    private int code;

    private String message;

    public ErrorCodeException(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public ErrorCodeException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
