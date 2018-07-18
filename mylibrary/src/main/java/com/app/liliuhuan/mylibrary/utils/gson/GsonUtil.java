package com.app.liliuhuan.mylibrary.utils.gson;

import com.google.gson.Gson;

/**
 * author: liliuhuan
 * date：2018/6/21
 * version:1.0.0
 * description: GsonUtil${DES} gson处理工具类
 */
public class GsonUtil {
    private static Gson instance;

    public static Gson getInstance() {
        if (null == instance) {
            synchronized (GsonUtil.class) {
                if (null == instance)
                    instance = new Gson();
            }
        }
        return instance;
    }

}
