package com.app.liliuhuan.armstest.api.upload;

import org.json.JSONObject;

import java.util.Map;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 上传
 */
public interface UploadService {
    /**
     * 上传单图
     *
     * @param description
     * @param file
     * @return
     */
    @Multipart
    @POST("")
    Observable<JSONObject> uploadFile(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    /**
     * 多文件上传
     *
     * @param params
     * @return
     */
    @Multipart
    @POST("")
    Call<ResponseBody> uploadFiles(@PartMap Map<String, RequestBody> params);
}
