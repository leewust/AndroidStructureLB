/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.liliuhuan.armstest.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.liliuhuan.armstest.R;
import com.app.liliuhuan.armstest.bean.User;
import com.app.liliuhuan.mylibrary.base.BaseViewHolder;
import com.app.liliuhuan.mylibrary.di.component.AppComponent;
import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;
import com.app.liliuhuan.mylibrary.utils.glide.GlideUtil;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * ================================================
 * 展示 {@link } 的用法
 * <p>
 * Created by JessYan on 9/4/16 12:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class UserItemHolder extends BaseViewHolder<User> {

    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_name)
    TextView mName;
    private AppComponent mAppComponent;

    public UserItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = CommonUtil.obtainAppComponentFromContext(itemView.getContext());

    }

    @Override
    public void setData(User data, int position) {
        Observable.just(data.getLogin())
                .subscribe(s -> mName.setText(s));

        GlideUtil.loadImage(data.getAvatarUrl(),mAvatar,R.drawable.ic_launcher_background);
    }


    @Override
    protected void onRelease() {
//        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
//                .imageViews(mAvatar)
//                .build());

    }
}
