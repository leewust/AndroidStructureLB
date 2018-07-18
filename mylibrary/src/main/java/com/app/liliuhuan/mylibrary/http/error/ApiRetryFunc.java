package com.app.liliuhuan.mylibrary.http.error;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @Description: 重试机制
 * @Author 杨建波
 * @Date 2017/11/6 17:33
 * @Email Android_panpan@163.com
 */
public class ApiRetryFunc implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public ApiRetryFunc(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                    if (++retryCount <= maxRetries && (throwable instanceof SocketTimeoutException
                            || throwable instanceof ConnectException)) {
//                        CommonLog.d("get response data error, it will try after " + retryDelayMillis
//                                + " millisecond, retry count " + retryCount);
                        return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                    }
                    return Observable.error(throwable);
                });
    }
}
