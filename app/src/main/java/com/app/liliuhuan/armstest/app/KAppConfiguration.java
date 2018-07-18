package com.app.liliuhuan.armstest.app;

import android.app.Application;
import android.content.Context;

import com.app.liliuhuan.armstest.BuildConfig;
import com.app.liliuhuan.armstest.api.Api;
import com.app.liliuhuan.mylibrary.delegate.app.AppLifecycles;
import com.app.liliuhuan.mylibrary.http.urlmanager.RetrofitUrlManager;
import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;

import butterknife.ButterKnife;
import timber.log.Timber;

import static com.app.liliuhuan.armstest.api.AppConfig.DoMainName_CUSTOM;

public class KAppConfiguration implements AppLifecycles {
    @Override
    public void attachBaseContext(Context base) {
        // TODO: 2018/4/24 安装热修复
      //  Beta.installTinker();
    }


    @Override
    public void onCreate(Application application) {
        // TODO: 2018/3/31    初始化URL
        initUrl();
        // TODO: 2018/1/18  初始化Log
        initLog();
        // TODO: 2018/4/27 初始化一个全局的监听器
        initContextListener();

        CommonUtil.init(application.getApplicationContext());
    }

    private void initContextListener() {
        // 远程扩展功能
//        CommonUtil.obtainAppComponentFromContext(CommonUtil.getAppContext()).appManager().setHandleListener((appManager, message) -> {
//            switch (message.what) {
//                case 0:
//                    /**
//                     * 每日一题---发布
//                     * {@link io.rong.imkit.plugins.DaySubjectPlugin#onClick(Fragment, RongExtension)}
//                     */
//                    Intent intent = new Intent(CommonUtil.getAppContext(), EverydaySubjectActivity.class);
//                    Bundle bundle = (Bundle) message.obj;
//                    intent.putExtras(bundle);
//                    CommonUtil.startActivity(intent);
//                    break;
//                case 1:
//                    /**
//                     * 每日一题---详情
//                     * {@link io.rong.imkit.widget.provider.DayexplainProvider#onItemClick(View, int, DayexplainMessage, UIMessage)}
//                     * {@link io.rong.imkit.widget.provider.DayTopicProvider#onItemClick(View, int, DayTopicMessage, UIMessage)}
//                     */
//                    Intent intent2 = new Intent(CommonUtil.getAppContext(), EverydaySubjectDetailActivity.class);
//                    Bundle bundle2 = (Bundle) message.obj;
//                    intent2.putExtras(bundle2);
//                    CommonUtil.startActivity(intent2);
//                    break;
//                case 3:
//                    /**
//                     * 帐号互踢
//                     * {@link IMClientManager#initConnectionStatusListener()}
//                     */
//                    CommonUtil.obtainAppComponentFromContext(CommonUtil.getAppContext()).appManager().killAll();
//                    UserInfoManager.getInstance().userLogout();
//                    Intent loginIntent = new Intent(CommonUtil.getAppContext(), LoginActivity.class);
//                    loginIntent.putExtra("IsShow", true);
//                    CommonUtil.startActivity(loginIntent);
//                    break;
//            }
//        });
        //使用方法:
        //Message msg = new Message();
        //msg.what = 0;
        //AppManager.post(msg);
    }



    private void initUrl() {
        RetrofitUrlManager.getInstance().putDomain(DoMainName_CUSTOM, Api.APP_DOMAIN);
    }

    @Override
    public void onTerminate(Application application) {

    }

    /**
     * 初始化Log设置
     */
    private void initLog() {
        if (BuildConfig.LOG_DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
            ButterKnife.setDebug(true);
        }
    }

}
