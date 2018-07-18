package com.app.liliuhuan.armstest.app.util;

import com.app.liliuhuan.mylibrary.base.mvp.IView;
import com.app.liliuhuan.mylibrary.http.error.ApiRetryFunc;
import com.app.liliuhuan.mylibrary.utils.lifecycle.RxLifecycleUtil;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: 提供网络请求装换
 * @Author 杨建波
 * @Date 2018/1/23 15:12
 * @Email Android_panpan@163.com
 */
public class ApiTransformer {
    /**
     * 失败重新连接次数
     */
    public static final int RETRY_COUNT = 3;
    /**
     * 失败重试时间间隔
     */
    public static final int RETRY_MILLIS = 2000;

    public static <T> ObservableTransformer<T, T> norTransformer(IView view) {
        return apiResultObservable -> apiResultObservable
                .subscribeOn(Schedulers.io())
                .retryWhen(new ApiRetryFunc(RETRY_COUNT,
                        RETRY_MILLIS))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtil.bindToLifecycle(view));
    }

    public static <T> ObservableTransformer<T, T> norTransformer(IView view, final int retryCount, final int retryDelayMillis) {
        return apiResultObservable -> apiResultObservable
                .subscribeOn(Schedulers.io())
                .retryWhen(new ApiRetryFunc(retryCount, retryDelayMillis))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtil.bindToLifecycle(view));
    }

    public static <T> ObservableTransformer<T, T> norTransformer() {
        return apiResultObservable -> apiResultObservable
                .subscribeOn(Schedulers.io())
                .retryWhen(new ApiRetryFunc(RETRY_COUNT, RETRY_MILLIS))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
