package com.app.liliuhuan.mylibrary.http.response;

public interface ResponseCode {

    // TODO: 2018/4/23 用户未登录 
    int UserNotLoginCode_5126 = 5126;

    // TODO: 2018/4/23 用户权限不足
    int UserNotPermissionCode_5122 = 5122;

    // TODO: 2018/4/23 帐号失效
    int UserAccountFailureCode_5127 = 5127;

    // TODO: 2018/2/7  网络请求错误码
    int UnknownCode_8000 = 8000;

    // TODO: 2018/4/23 VPN 无法连接异常code 
    int UnknownHostException_8001 = 8001;


    //*******************************************************************************************
    // TODO: 2018/2/7  错误码
    int HttpCode_307 = 307;
    int HttpCode_403 = 403;
    int HttpCode_404 = 404;
    int HttpCode_500 = 500;
    int HttpCode_504 = 504;
    int JsonCode_7000 = 7000;
    int NetCode_9000 = 9000;
    int NetCode_9001 = 9001;


}
