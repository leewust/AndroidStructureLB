package com.app.liliuhuan.mylibrary.http.progressmanager.body;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * 简单的文件上传进度监听
 */
public class ProgressRequestBodyV2 extends RequestBody {

    private MediaType contentType;
    private File file;
    private OnProgressListener listener;

    public ProgressRequestBodyV2(MediaType contentType, File file, OnProgressListener listener) {
        this.contentType = contentType;
        this.file = file;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return contentType;
    }

    @Override
    public long contentLength() {
        return file.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source;
        long len;//记录本次读了多少数据
        long currSize = 0;//记录目前一共读了多少数据
        long totalSize = contentLength();//一共有多少数据
        try {
            source = Okio.source(file);
            Buffer buffer = new Buffer();
            while ((len = source.read(buffer, 2048)) != -1) {
                sink.write(buffer, len);
                sink.flush();
                currSize += len;
                //回调,进度监听
                listener.onProgress(totalSize, currSize, totalSize == currSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface OnProgressListener {
        void onProgress(long totalSize, long currSize, boolean done);
    }
}
