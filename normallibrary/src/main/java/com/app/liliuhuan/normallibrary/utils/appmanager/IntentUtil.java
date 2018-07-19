package com.app.liliuhuan.normallibrary.utils.appmanager;

import android.content.Intent;

/**
 * author: liliuhuan
 * date：2018/6/21
 * version:1.0.0
 * description: IntentUtil${DES}
 */
public class IntentUtil {

    public static void start(Class<?> clazz) {
        AppManager.getInstance().startActivity(clazz);
    }

    public static void start(Intent intent) {
        AppManager.getInstance().startActivity(intent);
    }
}
