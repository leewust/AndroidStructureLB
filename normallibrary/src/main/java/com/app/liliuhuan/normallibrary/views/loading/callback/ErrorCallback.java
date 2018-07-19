package com.app.liliuhuan.normallibrary.views.loading.callback;

import com.app.liliuhuan.normallibrary.R;

/**
 * @Description: 通用的网络错误
 */
public class ErrorCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_defult_common_error;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

}
