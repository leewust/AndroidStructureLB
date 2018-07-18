package com.app.liliuhuan.mylibrary.utils.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 共享数据类
 */
public class PreferUtil {
    private static final String FILE_NAME = "share_date";
    private static SharedPreferences mCacheShareParences = null;
    private static Editor mCacheEditor;
    private static PreferUtil instance = null;

    private PreferUtil() {
        if (null == mCacheShareParences) {
            mCacheShareParences = CommonUtil.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            mCacheEditor = mCacheShareParences.edit();
        }
    }

    public static PreferUtil getInstance() {
        if (null == instance) {
            synchronized (PreferUtil.class) {
                if (null == instance)
                    instance = new PreferUtil();
            }
        }
        return instance;
    }

    //判断是否是第一次登录
    public boolean isFirstTime() {
        return mCacheShareParences.getBoolean("isFirstTime", true);
    }

    public void saveFirstTime(boolean flag) {
        mCacheEditor.putBoolean("isFirstTime", flag).apply();
    }
    public boolean isShowToast() {
        return mCacheShareParences.getBoolean("isShowToast", true);
    }

    public void saveShowToast(boolean flag) {
        mCacheEditor.putBoolean("isShowToast", flag).apply();
    }
    //存取token值
    public void saveToken(String token) {
        mCacheEditor.putString("token", token).apply();
    }

    public String getToken() {
        return mCacheShareParences.getString("token", "");
    }

    public void saveUserInfo(String userInfo) {
        mCacheEditor.putString("userInfo", userInfo).apply();
    }
    public String getUserInfo() {
        return mCacheShareParences.getString("userInfo", "");
    }

    public void clearData() {
        mCacheEditor.clear();
        mCacheEditor.commit();
        saveFirstTime(false);
    }
}
