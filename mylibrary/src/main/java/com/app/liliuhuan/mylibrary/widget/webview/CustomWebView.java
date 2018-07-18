package com.app.liliuhuan.mylibrary.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class CustomWebView extends WebView {

    public CustomWebView(Context context) {
        super(context);
       // setHorizontalScrollBarEnabled(false);//水平不显示
        WebSettings settings = getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setSupportZoom(false);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);//DOM Storage
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(false);
        String userAgentString = settings.getUserAgentString();
//        settings.setUserAgentString(userAgentString+" UxuejaC/"+ PackageUtil.getVersionName()+" NetType/"+ netWorkTypeString);
        settings.setSupportMultipleWindows(true);

    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // setHorizontalScrollBarEnabled(false);//水平不显示
        WebSettings settings = getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setSupportZoom(false);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);//DOM Storage
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(false);
//        settings.setUserAgentString(userAgentString+" UxuejaC/"+ PackageUtil.getVersionName()+" NetType/"+ netWorkTypeString);
        settings.setSupportMultipleWindows(true);
    }

}
