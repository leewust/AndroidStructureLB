package com.app.liliuhuan.net.util;

import android.text.TextUtils;

import com.app.liliuhuan.net.callback.json.IRequestCallBack;
import com.app.liliuhuan.net.callback.json.WrapperCallBack;
import com.app.liliuhuan.net.retrofit.RetrofitUtil;
import com.app.liliuhuan.net.service.UploadService;
import com.app.liliuhuan.normallibrary.utils.common.ToastUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 上传工具类
 */
public class UploadFileUtil {
    private static UploadFileUtil instance;

    private UploadFileUtil() {
    }

    public static UploadFileUtil getInstance() {
        if (null == instance) {
            instance = new UploadFileUtil();
        }
        return instance;
    }

    /**
     * 单文件上传
     *
     * @param uploadCallBack
     */
    public Call<ResponseBody> uploadFile(String filePath, final UploadCallBack uploadCallBack) {
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
        String descriptionString = "file";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        UploadService service = RetrofitUtil.getInstance().createService(UploadService.class);
        Call<ResponseBody> call = service.uploadFile(description, body);
        call.enqueue(new WrapperCallBack<ResponseBody>(new IRequestCallBack() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error) {
                uploadCallBack.uploadFailure(error);
            }
        }));
        return call;
    }

    /**
     * 多文件上传
     *
     * @param uploadCallBack
     */
    public Call<String> uploadFiles(List<String> filePathList, final UploadCallBack uploadCallBack) {
        if (filePathList == null) {
            ToastUtil.showToast("请选择要上传的图片");
            return null;
        }
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < filePathList.size(); i++) {
            if (!TextUtils.isEmpty(filePathList.get(i)) && !"add".equals(filePathList.get(i))) {
                File file = new File(filePathList.get(i));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                params.put("file\"; filename=\"" + i + file.getName(), requestBody);
            }
        }

        UploadService service = RetrofitUtil.getInstance().createService(UploadService.class);
        Call<String> call = service.uploadFiles(params);
        call.enqueue(new WrapperCallBack<String>(new IRequestCallBack() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error) {
                uploadCallBack.uploadFailure(error);
            }
        }));
        return call;
    }

    public interface UploadCallBack {
        void uploadSucess(List<String> list);

        void uploadFailure(String error);
    }
}
