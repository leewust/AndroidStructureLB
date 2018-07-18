package com.app.liliuhuan.armstest.di.component;

import dagger.Component;

import com.app.liliuhuan.armstest.di.module.UserModule;
import com.app.liliuhuan.armstest.mvp.ui.activity.UserActivity;
import com.app.liliuhuan.mylibrary.di.component.AppComponent;
import com.app.liliuhuan.mylibrary.di.scope.ActivityScope;

@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);
}