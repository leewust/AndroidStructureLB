package com.app.liliuhuan.mylibrary.widget.loading.callback;

import com.app.liliuhuan.mylibrary.R;

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
