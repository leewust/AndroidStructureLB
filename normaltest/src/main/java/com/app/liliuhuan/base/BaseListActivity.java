package com.app.liliuhuan.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.app.liliuhuan.net.callback.IGsonRequestCallBack;
import com.app.liliuhuan.normallibrary.base.BaseActivity;
import com.app.liliuhuan.normallibrary.base.BaseRecycleViewAdapter;
import com.app.liliuhuan.normallibrary.utils.common.CommonUtil;
import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;
import com.app.liliuhuan.normallibrary.views.loading.LoadSir;
import com.app.liliuhuan.normallibrary.views.loading.callback.Callback;
import com.app.liliuhuan.normallibrary.views.loading.callback.EmptyCallback;
import com.app.liliuhuan.normallibrary.views.loading.callback.ErrorCallback;
import com.app.liliuhuan.normallibrary.views.loading.callback.LoadingCallback;
import com.app.liliuhuan.normallibrary.views.loading.core.LoadService;
import com.app.liliuhuan.normaltest.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

public abstract class BaseListActivity<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        BaseRecycleViewAdapter.OnRecyclerViewItemClickListener<T>, Callback.OnReloadListener, IGsonRequestCallBack<String> {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    BaseRecycleViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public boolean useToolBar() {
        return true;
    }

    @Override
    public int initLoadResId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void initView() {
        swipeRefresh.setColorSchemeColors(
                CommonUtil.getColor(android.R.color.holo_blue_light),
                CommonUtil.getColor(android.R.color.holo_green_light),
                CommonUtil.getColor(android.R.color.holo_orange_light),
                CommonUtil.getColor(android.R.color.holo_red_light));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(getLayoutManager() == null ? layoutManager : getLayoutManager());
        recyclerView.setAdapter(adapter = getAdapter());

        setToolBarTitle(getCustomTitle());

        setListener();
    }

    private void setListener() {
        swipeRefresh.setOnRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void prepareData() {
        super.prepareData();
        HttpListUtil.getInstance().getHttpList(getHttpUrl(), getParams(), this);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    protected abstract BaseRecycleViewAdapter getAdapter();

    protected abstract String getHttpUrl();

    protected abstract Map<String, Object> getParams();

    protected abstract Class<T> getEntity();

    protected abstract String getCustomTitle();

    @Override
    public void onRefresh() {
        prepareData();
    }

    @Override
    public void onItemClick(View view, int viewType, T data, int position) {
    }

    @Override
    public void onReload(View v) {
        prepareData();
    }

    @Override
    public void onStartLoading() {
        showLoadingDialog();
    }

    @Override
    public void onSuccess(String result) {
        dismissLoadingDialog();
        if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
        ApiListResponse apiListResponse = ApiListResponse.fromJson(result, getEntity());
        List<T> data = apiListResponse.getData();
        adapter.setList(data);
    }

    @Override
    public void onError(String error) {
        dismissLoadingDialog();
        if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
        ToastUtil.showToast(error);
    }
}
