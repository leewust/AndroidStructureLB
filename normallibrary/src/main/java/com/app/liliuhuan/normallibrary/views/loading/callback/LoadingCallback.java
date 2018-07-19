package com.app.liliuhuan.normallibrary.views.loading.callback;


import android.content.Context;
import android.view.View;

import com.app.liliuhuan.normallibrary.R;

/**
 * @Description: 加载页面 （通用）
 * @Author 杨建波
 * @Date 2017/11/29 15:58
 * @Email Android_panpan@163.com
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_defult_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    public boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
