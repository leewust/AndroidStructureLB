package com.app.liliuhuan.normallibrary.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;


import com.app.liliuhuan.normallibrary.BaseApp;
import com.app.liliuhuan.normallibrary.R;
import com.app.liliuhuan.normallibrary.utils.appmanager.IntentUtil;
import com.app.liliuhuan.normallibrary.utils.common.CommonUtil;
import com.app.liliuhuan.normallibrary.utils.metrics.MetricsUtil;
import com.app.liliuhuan.normallibrary.views.toolbar.CustomToolbar;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private Unbinder unbinder;
    private CustomToolbar customToolbar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeContentView();
        setContentView(initLoadResId());
        MetricsUtil.setCustomDensity(this, BaseApp.getApplication());
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initDate();
        if (useToolBar()) initBar();
        initView();
        prepareData();
    }

    public void initBar() {
        customToolbar = findViewById(R.id.custom_toolbar);
        if (customToolbar == null)
            throw new NullPointerException("该activity必须包含toolbar自定义的布局");
        customToolbar.setLeftTitleClickListener(this);
    }

    public void setToolBarTitle(String resStr) {
        if (customToolbar != null) {
            customToolbar.setMainTitle(resStr);
        }
    }

    public void setRightTitleText(String resStr) {
        if (customToolbar != null) {
            customToolbar.setRightTitleText(resStr);
        }
    }

    public void setRightTitleClickListener(View.OnClickListener listener) {
        if (customToolbar != null) {
            customToolbar.setRightTitleClickListener(listener);
        }
    }

    public void setToolBarTitle(@StringRes int resStr) {
        if (customToolbar != null) {
            customToolbar.setMainTitle(getString(resStr));
        }
    }

    public void setImageRightClickListener(View.OnClickListener onClickListener) {
        if (customToolbar != null) {
            customToolbar.setImageRightClickListener(onClickListener);
        }
    }

    public void setRightTitleText(int res) {
        if (customToolbar != null) {
            customToolbar.setRightImageRes(res);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public abstract boolean useToolBar();

    protected void beforeContentView() { }

    public abstract int initLoadResId();

    protected void initDate() { }

    protected abstract void initView();

    protected void prepareData() { }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    public void showLoadingDialog() {
        showLoadingDialog("", false);
    }

    public void showLoadingDialog(String message, boolean cancelable) {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.setMessage(TextUtils.isEmpty(message) ? "加载中..." : message);
        if (!mProgressDialog.isShowing() && !isFinishing()) {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dismissLoadingDialog() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // MobclickAgent.onPause(this);
    }
}
