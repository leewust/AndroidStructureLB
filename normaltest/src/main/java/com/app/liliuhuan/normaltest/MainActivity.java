package com.app.liliuhuan.normaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.liliuhuan.bean.UserCateBean;
import com.app.liliuhuan.net.HttpUtil;
import com.app.liliuhuan.net.callback.IGsonRequestCallBack;
import com.app.liliuhuan.normallibrary.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    public boolean useToolBar() {
        return true;
    }

    @Override
    public int initLoadResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setToolBarTitle("normal");

        tvShow.setText("normal_test ");
    }

    @Override
    protected void prepareData() {
        super.prepareData();


        HttpUtil.getInstance().getUserType2(new IGsonRequestCallBack<List<UserCateBean>>() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(List<UserCateBean> result) {
                if (result != null && !result.isEmpty()) {
                    //   Log.e("tag==",result.toString());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
//

        HttpUtil.getInstance().getBanners(new IGsonRequestCallBack() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(Object result) {
                if (result != null) {
                    Log.e("tag==", result.toString());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
