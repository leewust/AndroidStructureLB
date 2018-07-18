package com.app.liliuhuan.mylibrary.utils.permission;

import android.Manifest;

/**
 * @Description: 定义需要运行时权限的权限名称
 * @Author 杨建波
 * @Date 2018/1/7 17:25
 * @Email Android_panpan@163.com
 */
public interface PermissionName {
    /**
     * 写入权限
     */
    String WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    /**
     * 读取存储权限
     */
    String READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;

    /**
     * 相机权限
     */
    String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    /**
     * 信息权限
     */
    String PERMISSION_SMS = Manifest.permission.SEND_SMS;

    /**
     * 拨打电话权限
     */
    String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;

    /**
     * 手机状态权限
     */
    String PERMISSION_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
}
