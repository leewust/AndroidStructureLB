package com.app.liliuhuan.armstest.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.liliuhuan.armstest.app.util.ApiTransformer;
import com.app.liliuhuan.armstest.bean.User;
import com.app.liliuhuan.armstest.mvp.contract.UserContract;
import com.app.liliuhuan.mylibrary.base.mvp.BasePresenter;
import com.app.liliuhuan.mylibrary.di.scope.ActivityScope;
import com.app.liliuhuan.mylibrary.http.AppManager;
import com.app.liliuhuan.mylibrary.http.error.ErrorHandleSubscriber;
import com.app.liliuhuan.mylibrary.http.error.core.RxErrorHandler;

import java.util.List;

import javax.inject.Inject;


@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;
    @Inject
    List<User> mUsers;
    @Inject
    RecyclerView.Adapter mAdapter;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;


    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        requestUsers(true);//打开 App 时自动加载列表
        getBannner();
    }

    private void getBannner() {
        mModel.getBanners().compose(ApiTransformer.norTransformer(mRootView)).subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
            @Override
            public void callBackSuccess(String jsonObject) {

            }

            @Override
            public void callBackError(int errCode, String errMsg) {

            }
        });
    }

    public void requestUsers(final boolean pullToRefresh) {


        if (pullToRefresh) lastUserId = 1;//下拉刷新默认只请求第一页

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次下拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }

        mModel.getUsers(lastUserId, isEvictCache)
                .compose(ApiTransformer.norTransformer(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<User>>(mErrorHandler) {
                    @Override
                    public void callBackSuccess(List<User> users) {
                        lastUserId = users.get(users.size() - 1).getId();//记录最后一个id,用于下一次请求
                        if (pullToRefresh) mUsers.clear();//如果是下拉刷新则清空列表
                        preEndIndex = mUsers.size();//更新之前列表总长度,用于确定加载更多的起始位置
                        mUsers.addAll(users);
                        if (pullToRefresh)
                            mAdapter.notifyDataSetChanged();
                        else
                            mAdapter.notifyItemRangeInserted(preEndIndex, users.size());
                    }

                    @Override
                    public void callBackError(int errCode, String errMsg) {
//                        CommonUtils.makeText(errMsg);
                        Log.e("tag====", errMsg);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAdapter = null;
        this.mUsers = null;
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
