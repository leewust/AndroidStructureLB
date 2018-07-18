package com.app.liliuhuan.mylibrary.utils.glide;

import android.widget.ImageView;

/**
 * @Author ：李刘欢
 * @Date ：2018/3/29
 * @Version : 1.0.0
 * @Description: 图片加载类
 */

public class GlideUtil {

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void loadImage(String url, ImageView imageView, int placeholder) {
        if (url != null) {
            GlideImageLoader.create(imageView).loadImage(url, placeholder);
        }
    }

    /**
     * 加载圆形
     *
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void loadCircleImage(String url, ImageView imageView, int placeholder) {
        GlideImageLoader.create(imageView).loadCircleImage(url, placeholder);
    }

    /**
     * 加载圆角
     *
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void loadRoundImage(String url, ImageView imageView, int placeholder) {
        GlideImageLoader.create(imageView).loadRoundImage(url, placeholder);
    }

    /** 加载圆角
     * @param url
     * @param imageView
     * @param placeholder
     * @param dip  圆角度数
      */
    public static void loadRoundImage(String url, ImageView imageView, int placeholder, int dip) {
        GlideImageLoader.create(imageView).loadRoundImage(url, placeholder, dip);
    }
}
