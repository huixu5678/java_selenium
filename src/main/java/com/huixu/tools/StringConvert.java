package com.huixu.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringConvert {
    /**
     * 将时间 按照格式 转换为Sting类型
     * @param date
     * @return String
     */
    public static String toString(Date date, String formaterString) {
        String timestr;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        timestr = formater.format(date);
        return timestr;
    }
    /**
     * 将时间字符串转换为Date类型
     * @param dateStr
     * @return Date
     */
    public static Date toDate(String dateStr) throws Exception{
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 按照提供的格式将字符串转换成Date类型
     * @param dateStr
     * @param formaterString
     * @return
     */
    public static Date toDate(String dateStr, String formaterString) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
