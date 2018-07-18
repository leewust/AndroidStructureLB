package com.app.liliuhuan.mylibrary.http.progressmanager.body;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * 简单的文件上传进度监听
 */
public class SliceRequestBodyV2 extends RequestBody {

    private MediaType contentType;
    private byte[] buffer;
    private int sliceSize;

    public SliceRequestBodyV2(MediaType contentType, byte[] buffer, int sliceSize) {
        this.contentType = contentType;
        this.buffer = buffer;
        this.sliceSize = sliceSize;
    }

    @Override
    public MediaType contentType() {
        return contentType;
    }

    @Override
    public long contentLength() {
        return sliceSize;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        sink.write(buffer);
        sink.flush();

//        Source source;
//        long len;//记录本次读了多少数据
//        long currSize = 0;//记录目前一共读了多少数据
//        long totalSize = contentLength();//一共有多少数据
//        try {
//            source = Okio.source(file);
//            Buffer buffer = new Buffer();
//            while ((len = source.read(buffer, 2048)) != -1) {
//                sink.write(buffer, len);
//                sink.flush();
//                currSize += len;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
