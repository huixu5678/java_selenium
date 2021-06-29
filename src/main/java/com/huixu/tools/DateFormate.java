package com.huixu.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class
DateFormate {

    public final static String SDF_YEAR = "yyyy";

    public final static String SDF_DAY = "yyyy-MM-dd";

    public final static String SDF_DAY_TIMES ="HH点mm分ss秒";

    public final static String SDF_DAYS = "yyyyMMdd";

    public final static String SDF_TIMES ="yyyy-MM-dd HH:mm:ss";

    public final static String SDF_TIME = "yyyy-MM-dd HH:mm";

    public final static String SDF_MONTH = "yyyy-MM";

    public static final String SN_TIMES = "yyyyMMddHHmmss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";


//    private static SimpleDateFormat simpleDateFormat;

    public static String format(String type){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(type);
        String s=simpleDateFormat.format(new Date());
        return s;
    }



    //返回一个时间戳
    public static String getTime(){
        long time=System.currentTimeMillis();//毫秒
        String strtime=String.valueOf(time);
        return strtime;
    }
}











