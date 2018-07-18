package com.app.liliuhuan.mylibrary.utils.glide.cache;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * author: liliuhuan
 * date：2018/6/21
 * version:1.0.0
 * description: CustomGlideModule${DES}
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {
    int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
    public static final String GLIDE_CARCH_DIR = "image_catch";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, GLIDE_CARCH_DIR, diskCacheSizeBytes));
    }
}
