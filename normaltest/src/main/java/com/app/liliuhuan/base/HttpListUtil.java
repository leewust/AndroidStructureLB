package com.app.liliuhuan.base;

import com.app.liliuhuan.net.callback.GsonWrapperCallBack;
import com.app.liliuhuan.net.callback.IGsonRequestCallBack;
import com.app.liliuhuan.net.retrofit.RetrofitUtil;
import com.app.liliuhuan.net.service.HttpService;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 处理请求工具类
 */
public class HttpListUtil {

    private static HttpListUtil instance;

    private HttpListUtil() {
    }

    public static HttpListUtil getInstance() {
        if (null == instance) {
            instance = new HttpListUtil();
        }
        return instance;
    }

    public Call<String> getHttpList(String path,Map<String,Object> params ,IGsonRequestCallBack callBack) {
        HttpService service = RetrofitUtil.getInstance().createService(HttpService.class);
        Call<String> httpList = service.getHttpList(path,params);
        httpList.enqueue(new GsonWrapperCallBack<>(callBack));
        return httpList;
    }

    public Call<String> postHttpList(String path,Map<String,Object> params ,IGsonRequestCallBack callBack) {
        HttpService service = RetrofitUtil.getInstance().createService(HttpService.class);
        Call<String> httpList = service.postHttpList(path,params);
        httpList.enqueue(new GsonWrapperCallBack<>(callBack));
        return httpList;
    }

}
