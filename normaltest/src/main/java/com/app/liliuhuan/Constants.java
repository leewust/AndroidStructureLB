package com.app.liliuhuan;


import com.app.liliuhuan.normaltest.BuildConfig;

/**
 * author: liliuhuan
 * date：2018/6/21
 * version:1.0.0
 * description: Constants${DES} 常量类
 */
public class Constants {
    public static String baseUrl = "http://demo.wukzk.com/limeng/pdzp/public/api/";
    static {
        if (BuildConfig.DEBUG_APK) {  /*如果是开发模式，则使用测试环境*/
            baseUrl = "http://117.50.44.242/pdzp/public/api/";
        } else {                        /*如果上线模式，则使用正式环境  */
            baseUrl = "http://117.50.44.242/pdzp/public/api/";
        }
    }

    // TODO: 2018/6/30 常用API
    public static final String URL_REGISTER = "User2/register";//POST
    public static final String URL_LOGIN = "User/login";//POST
    public static final String URL_userCate = "Other/userCate";//接口名称：获取用户种类
    public static final String URL_BANNER_LIST = "BannerInfo/index";//get

    public static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };
}