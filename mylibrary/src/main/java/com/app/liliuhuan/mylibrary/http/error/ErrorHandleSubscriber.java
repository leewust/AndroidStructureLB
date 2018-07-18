package com.app.liliuhuan.mylibrary.http.error;

import com.app.liliuhuan.mylibrary.http.error.core.RxErrorHandler;
import com.app.liliuhuan.mylibrary.http.error.exception.ErrorCodeException;
import com.app.liliuhuan.mylibrary.http.response.ResponseErrorUtil;
import com.app.liliuhuan.mylibrary.utils.Check;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @Description: 基于RaJava 封装观察者来处理返回结果
 * @Author 杨建波
 * @Date 2018/1/1 17:09
 * @Email Android_panpan@163.com
 */
public abstract class ErrorHandleSubscriber<T> extends DisposableObserver<T> {

    public ErrorHandleSubscriber(RxErrorHandler rxErrorHandler) {
        if (null != rxErrorHandler) {
            this.mHandlerFactory = rxErrorHandler.getHandlerFactory();
        }
    }

    private ErrorHandlerFactory mHandlerFactory;

    @Override
    public void onNext(T t) {
        callBackSuccess(t);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(@NonNull Throwable t) {

        if (t instanceof ErrorCodeException) {
            ErrorCodeException mErrorCodeException = ((ErrorCodeException) t);
            String mHandleKoolearnMsg = ResponseErrorUtil.handleErrorCode(mErrorCodeException.getCode());
            callBackError(mErrorCodeException.getCode(), Check.isEmpty(mHandleKoolearnMsg) ? mErrorCodeException.getMessage() : mHandleKoolearnMsg);
        } else {
            if (null != mHandlerFactory) {
                ErrorCodeException exception = mHandlerFactory.handleError(t);
                try {
                    callBackError(exception.getCode(), ResponseErrorUtil.handleErrorCode(exception.getCode()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract void callBackSuccess(T t);

    public abstract void callBackError(int errCode, String errMsg);
}
