package com.app.liliuhuan.mylibrary.http.error;

import android.content.Context;

import com.app.liliuhuan.mylibrary.http.error.exception.ErrorCodeException;
import com.app.liliuhuan.mylibrary.http.error.listener.ResponseErrorListener;

/**
 * @Description: 提供错误工程模式转换
 * @Author 杨建波
 * @Date 2018/1/1 17:07
 * @Email Android_panpan@163.com
 */
public class ErrorHandlerFactory {

    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
        this.mContext = mContext;
    }

    /**
     * 处理错误
     *
     * @param throwable
     */
    public ErrorCodeException handleError(Throwable throwable) {
        return mResponseErrorListener.handleResponseError(mContext, throwable);
    }
}
