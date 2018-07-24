package com.app.liliuhuan.net.service;


import com.app.liliuhuan.Constants;
import com.app.liliuhuan.bean.UserCateBean;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 接口管理
 */
public interface HttpService {

    /**
     * 注册
     *
     * @param phoneNumber 手机号
     * @param code        验证码
     * @param password    密码
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.URL_REGISTER)
    Call<JSONObject> registerUser(@Field("mobile") String phoneNumber, @Field("sms_code") int code, @Field("password") String password);

    @GET(Constants.URL_userCate)
    Call<JSONObject> getUserCate();

    @GET(Constants.URL_userCate)
    Call<List<UserCateBean>> getUserCate2();

    @GET(Constants.URL_BANNER_LIST)
    Call<List<UserCateBean>> getBanners();


    @GET
    Call<String> getHttpList(@Url String path, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    Call<JSONObject> postHttpList(@FieldMap Map<String, Object> map);
}
