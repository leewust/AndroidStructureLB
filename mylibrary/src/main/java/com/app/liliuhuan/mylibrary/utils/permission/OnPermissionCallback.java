package com.app.liliuhuan.mylibrary.utils.permission;

/**
 * @Description: 权限回调接口
 * @Author 杨建波
 * @Date 2017/10/18 18:40
 * @Email Android_panpan@163.com
 */
public interface OnPermissionCallback {
    //允许
    void onRequestAllow(String permissionName);

    //拒绝
    void onRequestRefuse(String permissionName);

    //不在询问
    void onRequestNoAsk(String permissionName);
}
