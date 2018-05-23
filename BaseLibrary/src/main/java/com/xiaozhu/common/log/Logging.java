package com.xiaozhu.common.log;

import android.content.Context;

import com.xiaozhu.common.log.crash.CrashExceptionLogger;
import com.xiaozhu.common.log.crash.CrashHandler;

/**
 * @说明 日志管理类
 * @作者 liuyi
 * @时间 2018/4/26 10:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class Logging {
    private static LoggingConfiguration loggingConfig;
    private static LoggingPrinter loggingPrinter;

    public static void init(Context context) {
        init(context, LoggingConfiguration.createDefault(context));
    }

    public static void init(Context context, LoggingConfiguration configuration) {
        if (loggingConfig == null) {
            loggingConfig = configuration;
            if (loggingConfig.isCrashHandlerOpen()) {
                CrashHandler crashHandler = new CrashHandler();
                crashHandler.init(loggingConfig.getOriginalHandler());
                crashHandler.setCaughtCrashExceptionListener(new CrashExceptionLogger(context));
            }
            if (loggingPrinter == null) {
                loggingPrinter = new LoggingPrinterImpl(loggingConfig);
            }
        }
    }

    public static LoggingConfiguration getLoggingConfig() {
        return loggingConfig;
    }

    public static void v(String msg, Object... args) {
        loggingPrinter.v(msg, args);
    }

    public static void v(String msg, Throwable throwable) {
        loggingPrinter.v(msg, throwable);
    }

    public static void d(String msg, Object... args) {
        loggingPrinter.d(msg, args);
    }

    public static void d(String msg, Throwable throwable) {
        loggingPrinter.d(msg, throwable);
    }

    public static void i(String msg, Object... args) {
        loggingPrinter.i(msg, args);
    }

    public static void i(String msg, Throwable throwable) {
        loggingPrinter.i(msg, throwable);
    }

    public static void w(String msg, Object... args) {
        loggingPrinter.w(msg, args);
    }

    public static void w(String msg, Throwable throwable) {
        loggingPrinter.w(msg, throwable);
    }

    public static void e(Throwable e) {
        loggingPrinter.e(e);
    }

    public static void e(String msg, Object... args) {
        loggingPrinter.e(msg, args);
    }

    public static void e(String msg, Throwable throwable, Object... args) {
        loggingPrinter.e(msg, throwable, args);
    }

    public static void wtf(String msg, Object... args) {
        loggingPrinter.wtf(msg, args);
    }

    public static void wtf(String msg, Throwable throwable) {
        loggingPrinter.wtf(msg, throwable);
    }

    public static void crash(String msg) {
        loggingPrinter.crash(msg);
    }

    public static void startMethod() {
        loggingPrinter.startMethod();
    }

    public static void endMethod() {
        loggingPrinter.endMethod();
    }
}
