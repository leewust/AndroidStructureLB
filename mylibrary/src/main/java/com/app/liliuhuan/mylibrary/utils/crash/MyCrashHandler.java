package com.app.liliuhuan.mylibrary.utils.crash;

import android.content.Context;
import android.util.Log;


import com.app.liliuhuan.mylibrary.utils.common.CommonUtil;
import com.app.liliuhuan.mylibrary.utils.date.DateUtil;
import com.app.liliuhuan.mylibrary.utils.permission.PermissionUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 自定义错误错误收集
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private Thread.UncaughtExceptionHandler mHandler;
    private static final String CONSTANT_STR = SystemUtil.getPhoneName()+"_"+SystemUtil.getOSVersion()+"_"
            + CommonUtil.getScreenWidth()+"*"+CommonUtil.getScreenHeigth();
    /**
     * 异常处理初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        // 获取系统默认的UncaughtException处理器
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        CommonLog.e(CONSTANT_STR+"_tid_"+thread.getId()+"_tname_"+thread.getName()+"_msg_"+ex.getMessage());
        // 自定义错误处理
        boolean hasDeal = handleException(thread,ex);
        if (!hasDeal && null!=mHandler) {// 如果用户没有处理则让系统默认的异常处理器来处理
            mHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
//            MobclickAgent.onKillProcess(mContext);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    private boolean handleException(Thread thread,Throwable ex) {
        String crash_msg = getCrashInfo(ex);
        String msg = "\ncrash__error__" +ex.getLocalizedMessage()
                + "\n \n phone_info__"+CONSTANT_STR
                +"\n\ntid__" +thread.getId()
                +"\n \ntname__" +thread.getName()
                +"\n \ncrash_msg__detail"+crash_msg;
        // TODO: 2018/7/18 友盟上传
//        MobclickAgent.reportError(CommonUtil.getAppContext(),msg);
        Log.e("upload_crash_log__lboc",msg);
        return false;
    }

    /**
     * 收集日志信息
     * @param ex
     * @return
     */
    private String getCrashInfo(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};
        boolean hasP = PermissionUtil.checkPermissions(mContext,permissions);
        if(hasP){
            String fileName ="myapp_" + DateUtil.getDateToString(System.currentTimeMillis(),"yyyy_MM_dd_HH_mm_ss")+ ".log";
            String path = FileUtil.getLogDir()+fileName;
            FileUtil.writeFile(sb.toString(),path,false);
        }
        return sb.toString();
    }
}
