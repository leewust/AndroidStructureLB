package com.app.liliuhuan.armstest.app;

import android.content.Context;

import com.app.liliuhuan.mylibrary.di.module.ClientModule;
import com.app.liliuhuan.mylibrary.http.progressmanager.ProgressManager;
import com.app.liliuhuan.mylibrary.http.urlmanager.RetrofitUrlManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @Description: TODO 配置OKhttp 统一处理
 * @Date 2018/4/2 18:40
 * @Email Android_panpan@163.com
 */
public class KOkHttpConfiguration implements ClientModule.OkhttpConfiguration {
    @Override
    public void configOkhttp(Context context, OkHttpClient.Builder builder) {
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        RetrofitUrlManager.getInstance().with(builder);
        ProgressManager.getInstance().with(builder);
    }
}
