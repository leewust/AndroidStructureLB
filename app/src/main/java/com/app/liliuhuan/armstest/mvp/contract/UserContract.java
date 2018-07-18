package com.app.liliuhuan.armstest.mvp.contract;

import android.app.Activity;

import com.app.liliuhuan.armstest.bean.User;
import com.app.liliuhuan.mylibrary.base.mvp.IModel;
import com.app.liliuhuan.mylibrary.base.mvp.IView;
import com.google.gson.JsonObject;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;

public interface UserContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void startLoadMore();
        void endLoadMore();
        Activity getActivity();
        //申请权限
        RxPermissions getRxPermissions();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<List<User>> getUsers(int lastIdQueried, boolean update);
        Observable<String> getBanners();
    }
}
