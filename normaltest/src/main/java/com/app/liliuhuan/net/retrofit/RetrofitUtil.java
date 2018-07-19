package com.app.liliuhuan.net.retrofit;


import com.app.liliuhuan.Constants;
import com.app.liliuhuan.net.gsonconvert.GsonConverterFactory;
import com.app.liliuhuan.net.interceptor.CommonInterceptor;
import com.app.liliuhuan.net.interceptor.HeaderInterceptor;
import com.app.liliuhuan.net.interceptor.LogInterceptor;
import com.app.liliuhuan.net.jsonconvert.JsonConverterFactory;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络请求的工具类
 */
public class RetrofitUtil {
    private static RetrofitUtil instance;
    private Retrofit helper;

    private RetrofitUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new CommonInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LogInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();

        helper = new Retrofit.Builder()
                .client(client)
               // .addConverterFactory(JsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (null == instance) {
            synchronized (RetrofitUtil.class) {
                if (null == instance)
                    instance = new RetrofitUtil();
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        T service = helper.create(clz);
        return service;
    }

}
