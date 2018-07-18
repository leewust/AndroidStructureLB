package com.app.liliuhuan.mylibrary.widget.loading.core;


import com.app.liliuhuan.mylibrary.widget.loading.callback.Callback;

public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
