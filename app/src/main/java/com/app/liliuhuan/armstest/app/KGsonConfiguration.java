package com.app.liliuhuan.armstest.app;

import android.content.Context;

import com.app.liliuhuan.mylibrary.di.module.AppModule;
import com.google.gson.GsonBuilder;


/**
 * @Description: 提供GSON配置
 * @Date 2018/3/23 16:08
 * @Email Android_panpan@163.com
 */
public class KGsonConfiguration implements AppModule.GsonConfiguration {
    @Override
    public void configGson(Context context, GsonBuilder builder) {
        builder.serializeNulls()
                .disableHtmlEscaping()
                .enableComplexMapKeySerialization();
    }
}