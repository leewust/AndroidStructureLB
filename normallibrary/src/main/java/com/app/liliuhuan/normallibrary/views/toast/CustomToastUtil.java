package com.app.liliuhuan.normallibrary.views.toast;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * ToastUtil toastUtil3 = new ToastUtil(context, R.layout.toast_center_horizontal);
 * toastUtil3.show(5000);
 *
 *
 *
 * author: liliuhuan
 * date：2018/6/25
 * version:1.0.0
 * description: CustomToastUtil${DES}
 */
public class CustomToastUtil {
    private Toast mToast;
    private TimeCount timeCount;
    private Handler mHandler = new Handler();
    private boolean canceled = true;
    public CustomToastUtil(Context context, int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(layoutId, null);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);
    }
    public void show() {
        mToast.show();
    }
    public void show(int duration) {
        timeCount = new TimeCount(duration, 1000);
        if (canceled) {
            timeCount.start();
            canceled = false;
            showUntilCancel();
        }
    }
    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
        canceled = true;
    }
    private void showUntilCancel() {
        if (canceled) { //如果已经取消显示，就直接return
            return;
        }
        mToast.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showUntilCancel();
            }
        }, Toast.LENGTH_LONG);
    }
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval); //millisInFuture总计时长，countDownInterval时间间隔(一般为1000ms)
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            mTextView.setText(message + ": " + millisUntilFinished / 1000 + "s后消失");
        }

        @Override
        public void onFinish() {
            hide();
        }
    }
}

