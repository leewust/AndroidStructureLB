package com.app.liliuhuan.normallibrary.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final SimpleDateFormat YMDHMS =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat YMD =
            new SimpleDateFormat("yyyy年M月d日", Locale.getDefault());
    private static final SimpleDateFormat MD =
            new SimpleDateFormat("M月d日", Locale.getDefault());
    private static final SimpleDateFormat YMDHM =
            new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.getDefault());
    private static final SimpleDateFormat YMDS =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat YMDHM2 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    public static final SimpleDateFormat YMD2 =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat HM =
            new SimpleDateFormat("hh:mm", Locale.getDefault());
    public static final SimpleDateFormat E =
            new SimpleDateFormat("E", Locale.getDefault());

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static long getDateToLong(String date ,String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date d  = sdf.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /*
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strMinute + " 分钟 " + strSecond + " 秒";
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTimeDHM(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
      //  if (day > 0) {
            sb.append(day + "天");
     //   }
       // if (hour > 0) {
            sb.append(hour + "时");
      //  }
      //  if (minute > 0) {
            sb.append(minute + "分");
      //  }
//        if(second > 0) {
//            sb.append(second+"秒");
//        }
//        if(milliSecond > 0) {
//            sb.append(milliSecond+"毫秒");
//        }
        return sb.toString();
    }
}
