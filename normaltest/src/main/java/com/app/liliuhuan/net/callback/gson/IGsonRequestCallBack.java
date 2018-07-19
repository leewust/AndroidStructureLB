package com.app.liliuhuan.net.callback.gson;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:
 */
public interface IGsonRequestCallBack<T> {

    /**
     * 请求前的回调
     */
    void onStartLoading();

    /**
     * 成功的回调
     * @param result
     */
    void onSuccess(T result);

    /**
     * 失败的回调
     * @param error
     */
    void onError(String error);

}
