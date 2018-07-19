package com.app.liliuhuan.normallibrary.utils.crash;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PermissionUtil {

    public PermissionUtil() {
    }

    /**
     * fragment中请求权限
     * @param fragment
     * @param permissions
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean requestPermissions(Fragment fragment, @NonNull String[] permissions) {
        return requestPermissions((Fragment) fragment, permissions, 0);
    }

    /**
     * fragment中请求权限
     * @param fragment
     * @param permissions
     * @param requestCode
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean requestPermissions(final Fragment fragment, @NonNull final String[] permissions, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (permissions != null && permissions.length != 0) {
            final FragmentActivity context = fragment.getActivity();
            ArrayList permissionsNotGranted = new ArrayList();
            final int[] requestResults = new int[permissions.length];
            boolean shouldShowRequestPermissionRationale = false;
            boolean result = false;

            for (int listener = 0; listener < permissions.length; ++listener) {
                requestResults[listener] = context.checkCallingOrSelfPermission(permissions[listener]);
                if (requestResults[listener] != 0) {
                    permissionsNotGranted.add(permissions[listener]);
                    if (!shouldShowRequestPermissionRationale && !context.shouldShowRequestPermissionRationale(permissions[listener])) {
                        shouldShowRequestPermissionRationale = true;
                    }
                }
            }

            if (shouldShowRequestPermissionRationale) {
                DialogInterface.OnClickListener callback = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case -2:
                                context.onRequestPermissionsResult(requestCode, permissions, requestResults);
                                break;
                            case -1:
                                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                context.startActivityForResult(intent, requestCode > 0 ? requestCode : -1);
                                fragment.requestPermissions(permissions, 400);
                                return;
                        }

                    }
                };
                showPermissionAlert(context, "您需要在设置中打开权限" + getNotGrantedPermissionMsg(context, permissionsNotGranted), callback);
            } else if (permissionsNotGranted.size() > 0) {
                context.requestPermissions((String[]) permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), requestCode);
            } else {
                result = true;
            }
            return result;
        } else {
            return true;
        }
    }

    /**
     * activity中请求权限
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean requestPermissions(Activity activity, @NonNull String[] permissions) {
        return requestPermissions(activity, permissions, 0);
    }

    /**
     * activity中请求权限
     * @param activity
     * @param permissions
     * @param requestCode
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean requestPermissions(final Activity activity, @NonNull final String[] permissions, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (permissions != null && permissions.length != 0) {
            ArrayList permissionsNotGranted = new ArrayList();
            final int[] requests = new int[permissions.length];
            boolean shouldShowRequestPermissionRationale = false;
            boolean result = false;

            for (int listener = 0; listener < permissions.length; ++listener) {
                requests[listener] = activity.checkCallingOrSelfPermission(permissions[listener]);
                if (requests[listener] != 0) {
                    permissionsNotGranted.add(permissions[listener]);
                    if (!shouldShowRequestPermissionRationale && !activity.shouldShowRequestPermissionRationale(permissions[listener])) {
                        shouldShowRequestPermissionRationale = true;
                    }
                }
            }

            if (shouldShowRequestPermissionRationale) {
                DialogInterface.OnClickListener callback = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_NEGATIVE:
                                activity.onRequestPermissionsResult(requestCode, permissions, requests);
                                dialog.dismiss();
                                break;
                            case DialogInterface.BUTTON_POSITIVE:
                                activity.requestPermissions(permissions, 400);
                                dialog.dismiss();
                                break;
                        }

                    }
                };
                showPermissionAlert(activity, "您需要在设置中打开权限" + getNotGrantedPermissionMsg(activity, permissionsNotGranted), callback);
            } else if (permissionsNotGranted.size() > 0) {
                activity.requestPermissions((String[]) permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), requestCode);
            } else {
                result = true;
            }
            return result;
        } else {
            return true;
        }
    }

    /**
     * 检查某个权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, @NonNull String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            if (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查一组权限
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissions(Context context, @NonNull String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (permissions != null && permissions.length != 0) {
            String[] arr = permissions;
            int len = permissions.length;
            for (int i = 0; i < len; ++i) {
                String permission = arr[i];
                if (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 权限提示dialog
     * @param context
     * @param permissions
     * @return
     */
    private static String getNotGrantedPermissionMsg(Context context, List<String> permissions) {
        HashSet permissionsValue = new HashSet();
        Iterator result = permissions.iterator();
        while (result.hasNext()) {
            String next = (String) result.next();
            int identifier = context.getResources().getIdentifier(next, "string", context.getPackageName());
           // CommonLog.e(identifier+"====="+next+"==="+context.getPackageName());
            if(identifier>0){
                String permissionValue = context.getString(identifier, new Object[]{Integer.valueOf(0)});
                permissionsValue.add(permissionValue);
            }
        }

        String result1 = "(";

        String value;
        for (Iterator iterator = permissionsValue.iterator(); iterator.hasNext(); result1 = result1 + value + " ") {
            value = (String) iterator.next();
        }

        result1 = result1.trim() + ")";
        return result1;
    }

    @TargetApi(11)
    private static void showPermissionAlert(Context context, String content, DialogInterface.OnClickListener listener) {
        (new AlertDialog.Builder(context)).setMessage(content).setPositiveButton("确定", listener).setNegativeButton("取消", listener).setCancelable(false).create().show();
    }
}
