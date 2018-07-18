package com.app.liliuhuan.mylibrary.utils.metrics;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * author: liliuhuan
 * date：2018/6/27
 * version:1.0.0
 * description: MetricsUtil${DES}  适配方案
 * 知识点
 * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 * 1. px = dp*160
 * 2. W-DP = W-px / dpi（像素密度） *160；
 * 3. DPI = W(平方)+ H(平方) 开根号 除以屏幕寸数
 */
public class MetricsUtil {
    private static float sNoncompatDensity;
    private static float sNoncompatScaleDensity;

    /**
     * 360dp的设计方案
     *
     * @param activity
     * @param application
     */
    public static void setCustomDensity(Activity activity, Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

            final float targetDensity = appDisplayMetrics.widthPixels / 360;
            final float targetScaleDensity = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
            final int targetDensityDpi = (int) (targetScaleDensity * 160);

            appDisplayMetrics.density = targetDensity;
            appDisplayMetrics.scaledDensity = targetDensity;
            appDisplayMetrics.densityDpi = targetDensityDpi;

            DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaleDensity;
            activityDisplayMetrics.densityDpi = targetDensityDpi;
        }
    }
}
