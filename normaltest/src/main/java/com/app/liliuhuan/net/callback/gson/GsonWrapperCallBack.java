package com.app.liliuhuan.net.callback.gson;


import android.util.Log;

import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:
 */
public class GsonWrapperCallBack<T> implements Callback<T> {

    private IGsonRequestCallBack callback;

    public GsonWrapperCallBack(IGsonRequestCallBack callback) {
        this.callback = callback;
        if (null != this.callback) callback.onStartLoading();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e("url==",call.request().url().toString());
        Log.e("response==",response.toString());
        String url = call.request().url().toString();
        try {
            if (response.isSuccessful() && response.code() == 200) {
                Log.e("url==response==",response.body().toString());
                T body = response.body();
                if (null != body) {
                   callback.onSuccess(body);
                }else {
                    if (null != callback) callback.onError(response.message());
                }
            } else {
                if (null != callback) callback.onError(response.message());
            }
        }catch (Exception e){
            ToastUtil.showToast(e.getMessage());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        try {
            if (null != callback) callback.onError(t.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
