package com.app.liliuhuan.armstest.app;


import com.app.liliuhuan.armstest.BuildConfig;

/**
 * @Description: TODO 配置Url环境地址
 * @Date 2018/4/27 17:22
 * @Email Android_panpan@163.com
 */
public class KUrlConfiguration {

    //*********************** 设置默认 URL APPID APPKEY  *****************
    public static String APP_BASE_URL = "";
    public static String APP_BASE_ID = "";
    public static String APP_BASE_KEY = "";

    //*********************** 开发环境 URL APPID APPKEY  *****************
    public static String APP_TRUNK_URL = "https://mobi.trunk.koolearn.com";
    public static String APP_TRUNK_ID = "320";
    public static String APP_TRUNK_KEY = "2629884bfe8e438c8b5c1cd97d201586";

    //*********************** 测试环境 URL APPID APPKEY  *****************
    public static String APP_NEIBU_URL = "https://mobi.neibu.koolearn.com";
    public static String APP_NEIBU_ID = "348";
    public static String APP_NEIBU_KEY = "0c27fede774e4d0b9b5058b220fdc806";

    //*********************** 预生产环境 URL APPID APPKEY  *****************
    public static String APP_TEST_URL = "https://mobitest.koolearn.com";
    public static String APP_TEST_ID = "161";
    public static String APP_TEST_KEY = "31d142ba6f484a4ea7cc3739d7250630";

    //*********************** 生产环境 URL APPID APPKEY  *****************
    public static String APP_RELEASE_URL = "https://mobi.koolearn.com";
    public static String APP_RELEASE_ID = "161";
    public static String APP_RELEASE_KEY = "31d142ba6f484a4ea7cc3739d7250630";


    /**
     * 1 : 开发环境  2：测试环境  3：预生产环境 4：生产环境
     */
    public static void init() {
        switch (BuildConfig.URL_TAG) {
            case 1:
                APP_BASE_URL = APP_TRUNK_URL;
                APP_BASE_ID = APP_TRUNK_ID;
                APP_BASE_KEY = APP_TRUNK_KEY;
                break;
            case 2:
                APP_BASE_URL = APP_NEIBU_URL;
                APP_BASE_ID = APP_NEIBU_ID;
                APP_BASE_KEY = APP_NEIBU_KEY;
                break;
            case 3:
                APP_BASE_URL = APP_TEST_URL;
                APP_BASE_ID = APP_TEST_ID;
                APP_BASE_KEY = APP_TEST_KEY;
                break;
            case 4:
                APP_BASE_URL = APP_RELEASE_URL;
                APP_BASE_ID = APP_RELEASE_ID;
                APP_BASE_KEY = APP_RELEASE_KEY;
                break;
            default:
                APP_BASE_URL = APP_NEIBU_URL;
                APP_BASE_ID = APP_NEIBU_ID;
                APP_BASE_KEY = APP_NEIBU_KEY;
                break;
        }
    }
}
