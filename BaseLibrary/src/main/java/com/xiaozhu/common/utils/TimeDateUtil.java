package com.xiaozhu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @说明 时间日期工具类
 * @作者 liuyi
 * @时间 2018/4/26 11:52
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class TimeDateUtil {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME = "MM-dd HH:mm";
    public static final String DEFAULT_TIME = "HH:mm";
    public static final String DEFAULT_DATE = "MM-dd";
    public static final String DEFAULT_YEAR = "yyyy";
    public static final String DEFAULT_MONTH = "MM";
    public static final String DEFAULT_DAY = "dd";
    public static final String DEFAULT_CN_DATE = "yyyy年MM月dd日";
    public static final String DEFAULT_CN_DATE_TIME = "yyyy年MM月dd日 HH:mm";
    public static final String DEFAULT_DATE_FOUNDATION = "yyyy-M-d";
    public static final String DEFAULT_YEAR_MONTH = "yyyy-MM";

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getToDate() {
        return formatDate(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getToDateTime() {
        return formatDate(DEFAULT_DATETIME_PATTERN);
    }


    /**
     * 格式化Date
     *
     * @param pattern 日期格式
     * @return pattern格式的时间
     */
    public static String formatDate(String pattern) {
        return formatDate(pattern, new Date());
    }

    /**
     * 格式化Date
     *
     * @param pattern 日期格式
     * @param date    时间Date
     * @return pattern格式的时间
     */
    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return simpleDateFormat.format(date);
    }
}
