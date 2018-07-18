package com.app.liliuhuan.armstest.app;

import android.content.Context;

import com.app.liliuhuan.armstest.app.convert.CustomGsonConverterFactory;
import com.app.liliuhuan.mylibrary.di.module.ClientModule;
import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;

import retrofit2.Retrofit;

/**
 * @Description: 通用的Retrofit 配置

 * @Date 2018/3/23 16:01
 * @Email Android_panpan@163.com
 */
public class KRetrofitConfiguration implements ClientModule.RetrofitConfiguration {
    @Override
    public void configRetrofit(Context context, Retrofit.Builder builder) {
        builder.addConverterFactory(CustomGsonConverterFactory.create(CommonUtil.obtainAppComponentFromContext(context).gson()));
    }
}
