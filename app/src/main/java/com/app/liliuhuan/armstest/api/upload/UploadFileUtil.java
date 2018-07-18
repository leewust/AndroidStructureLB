package com.app.liliuhuan.armstest.api.upload;

import android.text.TextUtils;

import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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
    public void uploadFile(String filePath, final UploadCallBack uploadCallBack) {
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
        String descriptionString = "file";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        CommonUtil
                .obtainAppComponentFromContext(CommonUtil.getAppContext())
                .repositoryManager()
                .obtainRetrofitService(UploadService.class)
                .uploadFile(description, body)
//                .compose(ApiTransformer.norTransformer(view))
//                .subscribe(new ErrorHandleSubscriber<String>(err)
//                {
//            @Override
//            public void callBackSuccess(String s) {
//
//            }
//
//            @Override
//            public void callBackError(int errCode, String errMsg) {
//
//            }
//        })
 ;
    }

    /**
     * 多文件上传
     *
     * @param uploadCallBack
     */
    public void uploadFiles(List<String> filePathList, final UploadCallBack uploadCallBack) {
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < filePathList.size(); i++) {
            if (!TextUtils.isEmpty(filePathList.get(i)) && !"add".equals(filePathList.get(i))) {
                File file = new File(filePathList.get(i));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                params.put("file\"; filename=\"" + i + file.getName(), requestBody);
            }
        }
        CommonUtil
                .obtainAppComponentFromContext(CommonUtil.getAppContext())
                .repositoryManager()
                .obtainRetrofitService(UploadService.class)
                .uploadFiles(params);
    }

    public interface UploadCallBack {
        void uploadSucess(List<String> list);

        void uploadFailure(String error);
    }
}
