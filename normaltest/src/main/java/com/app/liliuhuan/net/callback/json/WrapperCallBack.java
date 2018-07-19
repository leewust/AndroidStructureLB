package com.app.liliuhuan.net.callback.json;


import android.util.Log;


import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:
 */
public class WrapperCallBack<T> implements Callback<T> {

    private IRequestCallBack callback;

    public WrapperCallBack(IRequestCallBack callback) {
        this.callback = callback;
        if (null != this.callback) callback.onStartLoading();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e("url==",call.request().url().toString());
        String url = call.request().url().toString();
        try {
            if (response.isSuccessful() && response.code() == 200) {
                Log.e("url==response==",response.body().toString());
                Object obj = response.body();
                if (null != obj) {
                    String s = obj.toString();
                    JSONObject jsonObject= new JSONObject(s);
                    int code = jsonObject.optInt("code");
                    if (code == 1){
                        String optString = jsonObject.optString("data");
                        if (null != callback)
                            callback.onSuccess(optString);
                    }else {
                        if (url.contains("Other/receipt") || url.contains("ApplyInterview/checkHelp")){
                            if (null != callback) callback.onError(jsonObject.optString("data"));
                        }else {
                            if (null != callback) callback.onError(jsonObject.optString("msg"));
                        }
                    }
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
