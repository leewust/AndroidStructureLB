package com.app.liliuhuan.armstest.app;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.app.liliuhuan.armstest.BuildConfig;
import com.app.liliuhuan.armstest.api.AppConfig;
import com.app.liliuhuan.mylibrary.cache.IntelligentCache;
import com.app.liliuhuan.mylibrary.delegate.app.AppLifecycles;
import com.app.liliuhuan.mylibrary.di.module.GlobalConfigModule;
import com.app.liliuhuan.mylibrary.http.config.ConfigModule;
import com.app.liliuhuan.mylibrary.http.log.RequestInterceptor;
import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

/**
 * author: liliuhuan
 * date：2018/7/18
 * version:1.0.0
 * description: KGlobalConfiguration${DES}
 */
public class KGlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        // TODO: 2018/1/18 Release 时,让框架不再打印 Http 请求和响应的信息
        KUrlConfiguration.init();
        builder.baseurl(AppConfig.URL)
                // TODO: 2018/4/2 是否答应log
                .printHttpLogLevel(BuildConfig.LOG_DEBUG ? RequestInterceptor.Level.ALL : RequestInterceptor.Level.NONE)
                // TODO: 2018/4/2 配置Retrofit
                .retrofitConfiguration(new KRetrofitConfiguration())
                // TODO: 2018/4/2 配置请求头
                .globalHttpHandler(new KRequestConfiguration(context))
                // TODO: 2018/4/2 配置错误的结果统一处理
                .responseErrorListener(new KResponseConfiguration())
                // TODO: 2018/4/2 配置GSON处理装换器
                .gsonConfiguration(new KGsonConfiguration())
                // TODO: 2018/4/2 统一配置OKhttp
                .okhttpConfiguration(new KOkHttpConfiguration());
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        // AppLifecycles 的所有方法都会在基类 Application 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new KAppConfiguration());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        // ActivityLifecycleCallbacks 的所有方法都会在 Activity (包括三方库) 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new KActivityConfiguration());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                // 在配置变化的时候将这个 Fragment 保存下来,在 Activity 由于配置变化重建时重复利用已经创建的 Fragment。
                // https://developer.android.com/reference/android/app/Fragment.html?hl=zh-cn#setRetainInstance(boolean)
                // 如果在 XML 中使用 <Fragment/> 标签,的方式创建 Fragment 请务必在标签中加上 android:id 或者 android:tag 属性,否则 setRetainInstance(true) 无效
                // 在 Activity 中绑定少量的 Fragment 建议这样做,如果需要绑定较多的 Fragment 不建议设置此参数,如 ViewPager 需要展示较多 Fragment
                f.setRetainInstance(true);
            }

            @Override
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                ((RefWatcher) CommonUtil
                        .obtainAppComponentFromContext(f.getActivity())
                        .extras()
                        .get(IntelligentCache.KEY_KEEP + RefWatcher.class.getName()))
                        .watch(f);
            }
        });
    }
}
