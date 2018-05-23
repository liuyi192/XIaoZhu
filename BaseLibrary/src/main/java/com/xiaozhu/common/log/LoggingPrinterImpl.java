package com.xiaozhu.common.log;

import android.util.Log;

import com.xiaozhu.common.utils.AppUtils;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.utils.TimeDateUtil;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/26 11:20
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LoggingPrinterImpl implements LoggingPrinter {
    private LoggingConfiguration loggingConfiguration;
    public static final String METHOD_NAME = "printLog";
    private static final String METHOD_END_MSG_FORMAT = "\n=========================== %s end===========================\n";
    private static final String METHOD_START_MSG_FORMAT = "\n=========================== %s start===========================\n";

    public LoggingPrinterImpl(LoggingConfiguration loggingConfiguration) {
        this.loggingConfiguration = loggingConfiguration;
    }

    @Override
    public void v(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.V, null);
    }

    @Override
    public void v(String msg, Throwable throwable) {
        printLog(msg, LogLevel.V, throwable);
    }

    @Override
    public void d(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.D, null);
    }

    @Override
    public void d(String msg, Throwable throwable) {
        printLog(msg, LogLevel.D, throwable);
    }

    @Override
    public void i(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.I, null);
    }

    @Override
    public void i(String msg, Throwable throwable) {
        printLog(msg, LogLevel.I, throwable);
    }

    @Override
    public void w(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.W, null);
    }

    @Override
    public void w(String msg, Throwable throwable) {
        printLog(msg, LogLevel.W, throwable);
    }

    @Override
    public void e(Throwable throwable) {
        printLog(null, LogLevel.E, throwable);
    }

    @Override
    public void e(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.E, null);
    }

    @Override
    public void e(String msg, Throwable throwable, Object... args) {
        printLog(String.format(msg, args), LogLevel.E, throwable);
    }

    @Override
    public void wtf(String msg, Object... args) {
        printLog(String.format(msg, args), LogLevel.WTF, null);
    }

    @Override
    public void wtf(String msg, Throwable throwable) {
        printLog(msg, LogLevel.WTF, throwable);
    }

    @Override
    public void crash(String msg) {
        FileLogHelper.getInstance().logToFile(msg, null, LogLevel.E, true);
    }

    @Override
    public void startMethod() {
        printLog(String.format(METHOD_START_MSG_FORMAT, TimeDateUtil.getToDateTime() + "\t\t" + AppUtils.getMethodNameInfo("startMethod", 2)), LogLevel.D, null);
    }

    @Override
    public void endMethod() {
        printLog(String.format(METHOD_END_MSG_FORMAT, TimeDateUtil.getToDateTime() + "\t\t" + AppUtils.getMethodNameInfo("endMethod", 2)), LogLevel.D, null);
    }

    private void printLog(String msg, int logLevel, Throwable throwable) {
        if (msg == null && logLevel != LogLevel.E) {
            return;
        }
        if (allowConsoleLogPrint(logLevel)) {
            LogUtils.i(msg);
        }
        if (allowFileLogPrint(logLevel)) {
            FileLogHelper.getInstance().logToFile(msg, throwable, logLevel);
        }
    }

    private boolean allowConsoleLogPrint(int printLevel) {
        return loggingConfiguration.getConsoleLogLevel() <= printLevel;
    }

    private boolean allowFileLogPrint(int printLevel) {
        return loggingConfiguration.getFileLogLevel() <= printLevel;
    }
}
