package com.app.liliuhuan.mylibrary.http.error.core;

import android.content.Context;

import com.app.liliuhuan.mylibrary.http.error.ErrorHandlerFactory;
import com.app.liliuhuan.mylibrary.http.error.listener.ResponseErrorListener;


/**
 * @Description: 提供建造者模式的错误处理类
 * @Author 杨建波
 * @Date 2018/1/1 17:05
 * @Email Android_panpan@163.com
 */
public class RxErrorHandler {

    private ErrorHandlerFactory mHandlerFactory;

    private RxErrorHandler(Builder builder) {
        this.mHandlerFactory = builder.errorHandlerFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ErrorHandlerFactory getHandlerFactory() {
        return mHandlerFactory;
    }

    public static final class Builder {
        private Context context;
        private ResponseErrorListener mResponseErrorListener;
        private ErrorHandlerFactory errorHandlerFactory;

        private Builder() {
        }

        public Builder with(Context context) {
            this.context = context;
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener responseErrorListener) {
            this.mResponseErrorListener = responseErrorListener;
            return this;
        }

        public RxErrorHandler build() {
            if (context == null)
                throw new IllegalStateException("Context is required");

            if (mResponseErrorListener == null)
                throw new IllegalStateException("ResponseErrorListener is required");

            this.errorHandlerFactory = new ErrorHandlerFactory(context, mResponseErrorListener);

            return new RxErrorHandler(this);
        }
    }

}
