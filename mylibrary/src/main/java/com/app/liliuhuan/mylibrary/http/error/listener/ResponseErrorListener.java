package com.app.liliuhuan.mylibrary.http.error.listener;

import android.content.Context;

import com.app.liliuhuan.mylibrary.http.error.exception.ErrorCodeException;

/**
 * @Description: 暴露给外部单独处理的请求错误接口
 * @Author 杨建波
 * @Date 2018/1/1 15:28
 * @Email Android_panpan@163.com
 */
public interface ResponseErrorListener {

    ErrorCodeException handleResponseError(Context context, Throwable t);

    /**
     * 提供一个空的返回结果处理
     */
    ResponseErrorListener EMPTY = (context, t) -> new ErrorCodeException(10000, "this is empty ResponseErrorListener");
}
