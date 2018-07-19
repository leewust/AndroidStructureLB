package com.app.liliuhuan.normallibrary.utils.common;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 软件pan
 */
public class InputMethodUtil {

    private static InputMethodUtil instance;

    public static InputMethodUtil getInstance() {
        if(null==instance){
            synchronized (InputMethodUtil.class){
                if(null==instance)instance = new InputMethodUtil();
            }
        }
        return instance;
    }

    /**
     * 显示键盘
     * @param activity
     */
    public void showKeyboard(Activity activity){
        if(null==activity)return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 隐藏键盘
     * @param activity
     */
    public void hidenKeyboard(Activity activity){
        if (null == activity) return;
        InputMethodManager imm = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
        IBinder windowToken = activity.getWindow().getDecorView().getWindowToken();
        if (null != windowToken) {
            imm.hideSoftInputFromInputMethod(windowToken, 0);
        }
    }



    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     * @param view
     */
    public void hidenKeyboard(Activity activity, View view) {
        boolean isOpen = isKeyboardOpen(activity, view);
        if (isOpen) {
            InputMethodManager imm = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
            IBinder windowToken = activity.getWindow().getDecorView().getWindowToken();
            IBinder windowToken2 = view.getWindowToken();
            imm.hideSoftInputFromInputMethod(windowToken2, 0);
        }
    }

    /**
     * 键盘是否开启
     *
     * @param context
     * @param view
     * @return
     */
    public boolean isKeyboardOpen(Context context, View view) {
        boolean flag = false;
        if (context == null || view == null) {
            flag = false;
        } else {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            flag = imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return flag;
    }
}
