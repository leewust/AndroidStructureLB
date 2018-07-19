package com.app.liliuhuan.normallibrary.utils.common;

import android.text.TextUtils;
import android.widget.Toast;

import com.app.liliuhuan.normallibrary.BaseApp;


/**
 * Toast工具类
 */
public class ToastUtil {
    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(BaseApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(int resourse) {
        if (resourse == 0) {
            return;
        }
        Toast.makeText(BaseApp.getContext(), resourse, Toast.LENGTH_SHORT).show();
    }
}
