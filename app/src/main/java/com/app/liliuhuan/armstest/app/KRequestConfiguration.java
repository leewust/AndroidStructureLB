package com.app.liliuhuan.armstest.app;

import android.content.Context;

import com.app.liliuhuan.mylibrary.http.GlobalHttpHandler;
import com.app.liliuhuan.mylibrary.utils.Check;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @Description: 拦截请求信息做统一处理
 * @Date 2018/1/18 11:44
 * @Email Android_panpan@163.com
 */
public class KRequestConfiguration implements GlobalHttpHandler {
    private Context mContext;

    public KRequestConfiguration(Context context) {
        this.mContext = context;
    }

    @Override
    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
//        // TODO: 2018/4/23  因为拦截OkHttp的所有请求.避免一些没有对象出现空指针异常
//        if (Check.isEmpty(httpResult) || !CommonUtil.isJson(httpResult)) {
//            return response;
//        }
//        // TODO: 2018/4/23  先一步拦截服务器请求回来的信息，控制局部权限
//        ApiResult apiResult = CommonUtil.obtainAppComponentFromContext(mContext).gson().fromJson(httpResult, ApiResult.class);
//        if (apiResult.getCode() == UserNotLoginCode_5126) {
//            if (!Check.isEmpty(UserInfoManager.getInstance().getUserInfoDataMode().getSid())) {
//                CommonUtil.makeTextForMainThread(apiResult.getMessage());
//            }
//            UserInfoManager.getInstance().userLogout();
//            CommonUtil.obtainAppComponentFromContext(mContext).appManager().killAll();
//            CommonUtil.startActivity(LoginActivity.class);
//            return null;
//        }
//        // TODO: 2018/4/23   5122 权限不足   5217 帐号失效  如果当前页面是登录页面就提示不做跳转
//        if (apiResult.getCode() == UserNotPermissionCode_5122 || apiResult.getCode() == UserAccountFailureCode_5127|| apiResult.getCode() == 9708) {
//            UserInfoManager.getInstance().userLogout();
//            if (!LoginActivity.class.getCanonicalName().equals(CommonUtil.obtainAppComponentFromContext(mContext).appManager().getTopActivity().getPackageName()
//                    + "." + CommonUtil.obtainAppComponentFromContext(mContext).appManager().getTopActivity().getLocalClassName())) {
//                UserInfoManager.getInstance().userLogout();
//                CommonUtil.obtainAppComponentFromContext(mContext).appManager().killAll();
//                CommonUtil.startActivity(LoginActivity.class);
//            }
//            CommonUtil.makeTextForMainThread(apiResult.getMessage());
//            return null;
//        }
        return response;
    }

    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
        // TODO: 2018/1/18  可以在此处添加请求头
//        Request.Builder builder = chain.request().newBuilder();
//        builder.addHeader(APP_NAME, mContext.getApplicationInfo().processName);
//        builder.addHeader(APP_VERSION, AppInfoUtil.getVersionName());
//        builder.addHeader(APP_PVERSION, "1.1");
//        builder.addHeader(APP_IMEI, AppInfoUtil.getImei());
//        builder.addHeader(PHONE_PLATFORM, "android_phone_" + AppInfoUtil.getSystemVersion());
//        builder.addHeader(PHONE_MODEL, AppInfoUtil.getManufacturerName() + "_" + AppInfoUtil.getModelName());
//        builder.addHeader(PHONE_SCREEN_SIZE, CommonUtil.getScreenWidth() + "*" + CommonUtil.getScreenHeigth());
//        builder.addHeader(PHONE_NET, AppInfoUtil.getNetworkState());
//        if (UserInfoManager.getInstance().getUserInfoDataMode().getUserTypes() != null
//                && UserInfoManager.getInstance().getUserInfoDataMode().getUserTypes().size() != 0) {
//            if (UserInfoManager.getInstance().getUserInfoDataMode().isTeacher()) {
//                builder.addHeader(USER_TYPE, "0");
//            } else {
//                builder.addHeader(USER_TYPE, "1");
//            }
//        }
//        // TODO: 2018/1/24  再次拦截数据的请求信息
//        if (request.body() instanceof FormBody) {
//            FormBody.Builder bodyBuilder = new FormBody.Builder();
//            FormBody formBody = (FormBody) request.body();
//            Map<String, String> formBodyMap = new HashMap<>();
//            for (int i = 0; i < formBody.size(); i++) {
//                formBodyMap.put(formBody.encodedName(i), formBody.encodedValue(i));
//            }
//            Map<String, String> newBodyMap = NetworkManager.getInstance(mContext).getRequestMap(formBodyMap);
//            for (String key : newBodyMap.keySet()) {
//                bodyBuilder.addEncoded(key, newBodyMap.get(key));
//            }
//            return builder.post(bodyBuilder.build()).build();
//        }
        return chain.request().newBuilder().addHeader("client_type","android").build();

    }

}
