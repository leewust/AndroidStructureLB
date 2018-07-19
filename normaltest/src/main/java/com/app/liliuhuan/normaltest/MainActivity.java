package com.app.liliuhuan.normaltest;

import android.util.Log;

import com.app.liliuhuan.bean.UserCateBean;
import com.app.liliuhuan.net.HttpUtil;
import com.app.liliuhuan.net.callback.gson.IGsonRequestCallBack;
import com.app.liliuhuan.net.callback.json.IRequestCallBack;
import com.app.liliuhuan.normallibrary.base.BaseActivity;
import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;

import java.util.List;

public class MainActivity extends BaseActivity {


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
    }

    @Override
    protected void prepareData() {
        super.prepareData();
//        HttpUtil.getInstance().getUserType(new IRequestCallBack() {
//            @Override
//            public void onStartLoading() {
//                showLoadingDialog();
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                dismissLoadingDialog();
//             //   ToastUtil.showToast(result);
//            }
//
//            @Override
//            public void onError(String error) {
//                dismissLoadingDialog();
//                ToastUtil.showToast(error);
//            }
//        });

//        HttpUtil.getInstance().getUserType2(new IGsonRequestCallBack<List<UserCateBean>>() {
//            @Override
//            public void onStartLoading() {
//
//            }
//
//            @Override
//            public void onSuccess(List<UserCateBean> result) {
//                if (result != null && !result.isEmpty()){
//                 //   Log.e("tag==",result.toString());
//                }
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//

        HttpUtil.getInstance().getBanners(new IGsonRequestCallBack() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(Object result) {
                if (result != null ){
                    Log.e("tag==",result.toString());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
