package com.app.liliuhuan.normallibrary.utils.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description:  常用工具类
 */
public class CommonUtil {
    private static Context mContext;

    /**
     * 需要在application初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
    }


    /**
     * 获取应用程序名称
     *
     * @return
     */
    public static String getAppName() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return mContext.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * drawable to bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;

    }

    /**
     * Indicator设置两边艰间距
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        if (tabs != null) {
            Class<?> tabLayout = tabs.getClass();
            Field tabStrip = null;
            try {
                tabStrip = tabLayout.getDeclaredField("mTabStrip");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            try {
                llTab = (LinearLayout) tabStrip.get(tabs);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip,
                    Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip,
                    Resources.getSystem().getDisplayMetrics());
            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        }
    }

    /**
     * 注意使用的是application 的Context
     * 有些地方不可以使用，否则无法与Activity的生命周期无法绑定
     *
     * @return
     */
    public static Context getAppContext() {
        return mContext;
    }

    /**
     * 获取在String.xml 里面定义的text
     *
     * @param stringID id
     * @return String
     */
    public static String getString(int stringID) {
        return getResources().getString(stringID);
    }

    /**
     * 获得资源
     */
    public static Resources getResources() {
        return mContext.getResources();
    }

    /**
     * dip转pix
     *
     * @param dpValue
     * @return
     */
    public static int dip2pix(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * pix转dip
     */
    public static int pix2dip(int pix) {
        final float densityDpi = getResources().getDisplayMetrics().density;
        return (int) (pix / densityDpi + 0.5f);
    }

    /**
     * 得到字符数组
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /**
     * 从 dimens 中获得尺寸
     *
     * @param id
     * @return
     */
    public static int getDimens(int id) {
        return (int) getResources().getDimension(id);
    }

    /**
     * 获得颜色
     */
    public static int getColor(int rid) {
        return getResources().getColor(rid);
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    public static int getScreenHeigth() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 通过资源id获得drawable
     *
     * @param rID
     * @return
     */
    public static Drawable getDrawablebyResource(int rID) {
        return getResources().getDrawable(rID);
    }

    public static View inflate(Context context, int layoutID) {
        return inflate(context, layoutID, null, false);
    }

    public static View inflate(Context context, int layoutID, ViewGroup parent) {
        return inflate(context, layoutID, parent, false);
    }

    public static View inflate(Context context, int layoutID, ViewGroup parent, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(layoutID, parent, attachToRoot);
    }

    /**
     * 读取Assets下的txt文件
     *
     * @return
     */
    public static String readAssetsTxt(String fileName) {
        try {
            InputStream is = mContext.getAssets().open(fileName + ".txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf-8");
            return text;
        } catch (IOException e) {
            // Should never happen!
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }

    public static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    public static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";

    /**
     * @param res
     * @param key
     * @return 反射得到状态栏或导航栏的高度
     */
    public static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int resourceId = Integer.parseInt(clazz.getField(key).get(object).toString());
            if (resourceId > 0)
                result = res.getDimensionPixelSize(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断是否是json传
     *
     * @param value
     * @return
     */
    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static boolean isLogin() {
        boolean isLogin = false;
        String token = PreferUtil.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            isLogin = true;
        }
        return isLogin;
    }


}
