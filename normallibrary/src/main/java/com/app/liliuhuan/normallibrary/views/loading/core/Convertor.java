package com.app.liliuhuan.normallibrary.views.loading.core;

import com.app.liliuhuan.normallibrary.views.loading.callback.Callback;

public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
