package com.app.liliuhuan.normallibrary.utils.glide;

import android.widget.ImageView;

import com.app.liliuhuan.normallibrary.BaseApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * @Author ：李刘欢
 * @Date ：2018/3/29
 * @Version : 1.0.0
 * @Description: 图片加载类
 */

public class GlideUtil {

    /** 加载图片
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void loadImage(Object url, ImageView imageView, int placeholder) {
        if (url != null) {
            Glide.with(BaseApp.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholder).error(placeholder))
                    .into(imageView);
        }
    }

    /** 加载圆形
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void loadCircleImage(String url, ImageView imageView, int placeholder) {
        Glide.with(BaseApp.getContext())
                .load(url)
                .apply(new RequestOptions().circleCrop().error(placeholder).placeholder(placeholder))
                .into(imageView);
    }

    /** 加载圆角
     * @param url
     * @param imageView
     * @param placeholder
     * @param dip
     */
    public static void loadRoundImage(String url, ImageView imageView, int placeholder, int dip) {
        Glide.with(BaseApp.getContext())
                .load(url)
                .apply(bitmapTransform(new GlideRoundTransform(BaseApp.getContext())))
                .into(imageView);
    }
}
