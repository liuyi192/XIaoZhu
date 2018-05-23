package com.xiaozhu.common.utils;

import android.util.Log;

import com.xiaozhu.common.BuildConfig;

/**
 * @说明 日志输出工具
 * @作者 liuyi
 * @时间 2018/4/12 13:54
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LogUtils {
    private static final String TAG = "INFO";

    /**
     * 输出信息
     *
     * @param msg 消息
     */
    public static void i(String msg) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, msg);
    }

    /**
     * 输出测试消息
     *
     * @param msg 消息
     */
    public static void d(String msg) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, msg);
    }

    /**
     * 输出错误消息
     *
     * @param msg       消息
     * @param throwable 异常
     */
    public static void e(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, msg, throwable);
    }
}
