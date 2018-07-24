package com.app.liliuhuan.test;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.liliuhuan.bean.UserCateBean;
import com.app.liliuhuan.normallibrary.base.BaseRecycleViewAdapter;
import com.app.liliuhuan.normallibrary.base.BaseRecycleViewHolder;
import com.app.liliuhuan.normaltest.R;

import butterknife.BindView;

/**
 * author: liliuhuan
 * date：2018/7/24
 * version:1.0.0
 * description: TestAdapter${DES}
 */
public class TestAdapter extends BaseRecycleViewAdapter<UserCateBean> {

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public int getConvertViewId(int viewTypeviewType) {
        return R.layout.item_test_list_layout;
    }

    @Override
    public BaseRecycleViewHolder<UserCateBean> getNewHolder(View view) {
        return new TestViewHolder(view);
    }

    public class TestViewHolder extends BaseRecycleViewHolder<UserCateBean> {
        TextView tvName;

        public TestViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
        }

        @Override
        public void loadData(UserCateBean data, int position) {
            if (data == null) return;
            tvName.setText(data.getName());
        }
    }
}
