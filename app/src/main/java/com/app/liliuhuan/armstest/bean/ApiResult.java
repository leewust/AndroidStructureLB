package com.app.liliuhuan.armstest.bean;

import com.app.liliuhuan.armstest.api.AppConfig;

/**
 * author: liliuhuan
 * date：2018/7/18
 * version:1.0.0
 * description: ApiResult${DES}
 */
public class ApiResult {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code == AppConfig.HTTP_SUCCESS;
    }
}
