package com.app.liliuhuan.test;

import android.view.View;

import com.app.liliuhuan.Constants;
import com.app.liliuhuan.base.BaseListActivity;
import com.app.liliuhuan.bean.UserCateBean;
import com.app.liliuhuan.normallibrary.base.BaseRecycleViewAdapter;
import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * author: liliuhuan
 * date：2018/7/24
 * version:1.0.0
 * description: TestActivity${DES}
 */
public class TestActivity extends BaseListActivity<UserCateBean> {
    @Override
    protected BaseRecycleViewAdapter getAdapter() {
        return new TestAdapter(this);
    }

    @Override
    protected String getHttpUrl() {
        return Constants.URL_userCate;
    }

    @Override
    protected Map<String, Object> getParams() {
        return new HashMap<>();
    }

    @Override
    protected Class<UserCateBean> getEntity() {
        return UserCateBean.class;
    }

    @Override
    protected String getCustomTitle() {
        return "测试base_list_activity";
    }

    @Override
    public void onItemClick(View view, int viewType, UserCateBean data, int position) {
        super.onItemClick(view, viewType, data, position);
        ToastUtil.showToast("点击了-->"+ data.getName());
    }
}
