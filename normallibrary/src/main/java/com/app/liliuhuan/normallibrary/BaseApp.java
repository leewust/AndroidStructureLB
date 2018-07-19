package com.app.liliuhuan.normallibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.app.liliuhuan.normallibrary.utils.appmanager.AppManager;
import com.app.liliuhuan.normallibrary.utils.common.CommonUtil;
import com.app.liliuhuan.normallibrary.views.loading.LoadSir;
import com.app.liliuhuan.normallibrary.views.loading.callback.EmptyCallback;
import com.app.liliuhuan.normallibrary.views.loading.callback.ErrorCallback;
import com.app.liliuhuan.normallibrary.views.loading.callback.LoadingCallback;

/**
 * author: liliuhuan
 * date：2018/6/21
 * version:1.0.0
 * description: BaseApp${DES}
 */
public class BaseApp extends Application {
    private static Context context;
    public static BaseApp application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;
        // TODO: 2018/6/22 初始化
        initCommonUtil(context);
        // TODO: 2018/6/22 loading
        initLoadingLayout();
        // TODO: 2018/6/22 activity 管理
        AppManager.init(this);
        // TODO: 2018/6/22 生命周期控制
        setCallBack();

    }
    public static Context getContext() {
        return context;
    }

    public static BaseApp getApplication() {
        return application;
    }

    private void initCommonUtil(Context context) {
        CommonUtil.init(context);
    }

    private void initLoadingLayout() {
        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }


    private void setCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }

}
