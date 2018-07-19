package com.app.liliuhuan.net;
import com.app.liliuhuan.bean.UserCateBean;
import com.app.liliuhuan.net.callback.GsonWrapperCallBack;
import com.app.liliuhuan.net.callback.IGsonRequestCallBack;
import com.app.liliuhuan.net.retrofit.RetrofitUtil;
import com.app.liliuhuan.net.service.HttpService;
import java.util.List;
import retrofit2.Call;

/**
 * @author: liliuhuan
 * @date：
 * @version:1.0.0
 * @description: 处理请求工具类
 */
public class HttpUtil {

    private static HttpUtil instance;

    private HttpUtil() {
    }

    public static HttpUtil getInstance() {
        if (null == instance) {
            instance = new HttpUtil();
        }
        return instance;
    }

    public Call<List<UserCateBean>> getUserType2(IGsonRequestCallBack callBack) {
        HttpService service = RetrofitUtil.getInstance().createService(HttpService.class);
        Call<List<UserCateBean>> userCate2 = service.getUserCate2();
        userCate2.enqueue(new GsonWrapperCallBack<>(callBack));
        return userCate2;
    }

    public Call<List<UserCateBean>> getBanners(IGsonRequestCallBack callBack) {
        HttpService service = RetrofitUtil.getInstance().createService(HttpService.class);
        Call<List<UserCateBean>> userCate2 = service.getBanners();
        userCate2.enqueue(new GsonWrapperCallBack<>(callBack));
        return userCate2;
    }
}
