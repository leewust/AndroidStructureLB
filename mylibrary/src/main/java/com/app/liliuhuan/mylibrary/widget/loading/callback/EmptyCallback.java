package com.app.liliuhuan.mylibrary.widget.loading.callback;

import com.app.liliuhuan.mylibrary.R;

/**
 * @Description: 通用的数据为空
 */
public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_defult_common_empty;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

}
