package com.app.liliuhuan.net.interceptor;


import com.app.liliuhuan.normallibrary.utils.common.PreferUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    public RequestInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder request_builder = request.newBuilder();
        if ("GET".equals(request.method())) {
            HttpUrl.Builder builder = request.url().newBuilder();
            HttpUrl build = builder
//                    .addQueryParameter("source", Constants.source)
//                    .addQueryParameter("appVersion", "101")
                    .addQueryParameter("token", PreferUtil.getInstance().getToken())
                    .build();
            request = request_builder.url(build).build();
        } else if ("POST".equals(request.method())) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i), body.encodedValue(i));
                }
                body = builder
//                        .add("source", Constants.source)
//                        .add("appVersion", "101")
                        .add("token", PreferUtil.getInstance().getToken())
                        .build();
                request = request_builder.post(body).build();

            } else if (request.body() instanceof MultipartBody) {
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder
//                        .addFormDataPart("source", "android")
//                        .addFormDataPart("appVersion", "101")
                        .addFormDataPart("token", PreferUtil.getInstance().getToken());
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request = request_builder.post(builder.build()).build();
            }
        }

        Response response = chain.proceed(request);
        return response;
    }
}
