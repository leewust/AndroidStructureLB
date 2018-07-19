package com.app.liliuhuan.net.interceptor;


import com.app.liliuhuan.normallibrary.utils.common.PreferUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器（配置统一请求参数）
 */
public class CommonInterceptor implements Interceptor {

    public CommonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //参数就要针对body做操作,我这里针对From表单做操作
        if (request.body() instanceof FormBody) {
          //  Log.e("request==",request.body().toString());
            // 构造新的请求表单
            FormBody.Builder builder = new FormBody.Builder();

            FormBody body = (FormBody) request.body();
            //将以前的参数添加
            for (int i = 0; i < body.size(); i++) {
                builder.add(body.encodedName(i), body.encodedValue(i));
            }
            //追加新的参数
            builder.add("token", PreferUtil.getInstance().getToken());
            request = request.newBuilder().post(builder.build()).build();//构造新的请求体
        }else   {
            //如果是get请求
            HttpUrl url = request.url();
            HttpUrl newUrl = url.newBuilder()
                    .addQueryParameter("token", PreferUtil.getInstance().getToken())
                    .build();
            request = request.newBuilder().url(newUrl).build();
        }


        Response response = chain.proceed(request);
        return response;
    }
}
