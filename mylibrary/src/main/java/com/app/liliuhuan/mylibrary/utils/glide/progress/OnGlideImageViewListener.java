package com.app.liliuhuan.mylibrary.utils.glide.progress;

import com.bumptech.glide.load.engine.GlideException;

public interface OnGlideImageViewListener {

    void onProgress(int percent, boolean isDone, GlideException exception);
}
