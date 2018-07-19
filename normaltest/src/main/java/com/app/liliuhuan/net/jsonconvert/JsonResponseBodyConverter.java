package com.app.liliuhuan.net.jsonconvert;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        //解密
        //转换
        JSONObject bean= null;
        if(null!=responseBody){
            String response=responseBody.string();
           // Logger.e("response_json==="+response.toString());
            try {
                bean = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) bean;
    }
}
