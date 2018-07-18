package com.app.liliuhuan.armstest.app.convert;

import android.text.TextUtils;

import com.app.liliuhuan.armstest.bean.ApiResult;
import com.app.liliuhuan.mylibrary.http.error.exception.ErrorCodeException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Type mType;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        if (response.contains("code") && response.contains("msg")){
            return (T) parseApiResult(response);
//            ApiResult apiResult = gson.fromJson(response, ApiResult.class);
//            if (!apiResult.isSuccess()) {
//                value.close();
//                throw new ErrorCodeException(apiResult.getCode(), apiResult.getMsg());
//            }
//            if (mType != null && mType.equals(String.class)) {
//                try {
//                    return (T) response;
//                } finally {
//                    value.close();
//                }
//            } else if (mType != null && mType.equals(JSONObject.class)) {
//                JSONObject json = null;
//                try {
//                    json = new JSONObject(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    return (T) json;
//                } finally {
//                    value.close();
//                }
//            } else {
//                try {
//                    return gson.fromJson(parseApiResult(response), mType);
//                } finally {
//                    value.close();
//                }
//            }
        }else {
            return  gson.fromJson(response,mType);
        }
    }

    private String parseApiResult(String json) {
        if (TextUtils.isEmpty(json)) return null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.optString("data", "");
    }
}
