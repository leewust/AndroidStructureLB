package com.app.liliuhuan.mylibrary.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description: 辅助判断
 * @Author 杨建波
 * @Date 2017/11/6 18:08
 * @Email Android_panpan@163.com
 */
public class Check {

    public static boolean isEmpty(CharSequence str) {
        return isNull(str) || str.length() == 0;
    }

    public static boolean isEmpty(Object[] os) {
        return isNull(os) || os.length == 0;
    }

    public static boolean isEmpty(Collection<?> l) {
        return isNull(l) || l.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return isNull(m) || m.isEmpty();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }
}
