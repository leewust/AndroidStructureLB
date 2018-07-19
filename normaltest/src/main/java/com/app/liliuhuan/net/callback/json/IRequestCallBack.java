package com.app.liliuhuan.net.callback.json;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:
 */
public interface IRequestCallBack {

    /**
     * 请求前的回调
     */
    void onStartLoading();

    /**
     * 成功的回调
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败的回调
     * @param error
     */
    void onError(String error);

}
