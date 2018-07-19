package com.app.liliuhuan.net.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("client_type", "android")
                .build();
        return chain.proceed(request);
    }
}
