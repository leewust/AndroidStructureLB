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
package com.app.liliuhuan.armstest.app;

import android.content.Context;
import android.net.ParseException;

import com.app.liliuhuan.armstest.R;
import com.app.liliuhuan.mylibrary.http.error.exception.ErrorCodeException;
import com.app.liliuhuan.mylibrary.http.error.listener.ResponseErrorListener;
import com.app.liliuhuan.mylibrary.utils.ArmsUtils;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;


import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import timber.log.Timber;

import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.HttpCode_307;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.HttpCode_403;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.HttpCode_404;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.HttpCode_500;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.HttpCode_504;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.JsonCode_7000;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.NetCode_9000;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.NetCode_9001;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.UnknownCode_8000;
import static com.app.liliuhuan.mylibrary.http.response.ResponseCode.UnknownHostException_8001;

/**
 * ================================================
 * 展示 {@link ResponseErrorListener} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class KResponseConfiguration implements ResponseErrorListener {

    @Override
    public ErrorCodeException handleResponseError(Context context, Throwable t) {
        Timber.tag("Catch-Error").w(t.getMessage());
        int errorCode = UnknownCode_8000;
        ErrorCodeException mException = new ErrorCodeException(errorCode, "Unknown");
        if (t instanceof UnknownHostException) {
            mException.setCode(UnknownHostException_8001);
        } else if (t instanceof ConnectException) {
            mException.setCode(NetCode_9000);
            mException.setMessage("网络不可用");
        } else if (t instanceof SocketTimeoutException) {
            mException.setCode(NetCode_9001);
            mException.setMessage("网络不可用");
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            convertStatusCode(context, httpException, mException);
        } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
            mException.setCode(JsonCode_7000);
//            mException.setMessage(CommonUtil.getString(R.string.response_json_error_msg));
        }
        return mException;
    }

    private void convertStatusCode(Context context, HttpException exception, ErrorCodeException mException) {
        switch (exception.code()) {
            case HttpCode_307:
                mException.setCode(HttpCode_307);
//                mException.setMessage(CommonUtil.getString(R.string.response_http_307_error_msg));
                break;
            case HttpCode_403:
                mException.setCode(HttpCode_403);
//                mException.setMessage(CommonUtil.getString(R.string.response_http_403_error_msg));
                break;
            case HttpCode_404:
                mException.setCode(HttpCode_404);
//                mException.setMessage(CommonUtil.getString(R.string.response_http_404_error_msg));
                break;
            case HttpCode_500:
                mException.setCode(HttpCode_500);
//                mException.setMessage(CommonUtil.getString(R.string.response_http_500_error_msg));
                break;
            case HttpCode_504:
                mException.setCode(HttpCode_504);
//                mException.setMessage(CommonUtil.getString(R.string.response_http_504_error_msg));
                break;
        }
    }

}
