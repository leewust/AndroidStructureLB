package com.app.liliuhuan.mylibrary.utils.glide.progress;

import com.bumptech.glide.load.engine.GlideException;

public interface OnProgressListener {

    void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception);
}
