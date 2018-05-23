package com.xiaozhu.common.log;

/**
 * @说明 日志管理打印
 * @作者 liuyi
 * @时间 2018/4/26 10:54
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface LoggingPrinter {
    void v(String msg, Object... args);

    void v(String msg, Throwable throwable);

    void d(String msg, Object... args);

    void d(String msg, Throwable throwable);

    void i(String msg, Object... args);

    void i(String msg, Throwable throwable);

    void w(String msg, Object... args);

    void w(String msg, Throwable throwable);

    void e(Throwable throwable);

    void e(String msg, Object... args);

    void e(String msg, Throwable throwable, Object... args);

    void wtf(String msg, Object... args);

    void wtf(String msg, Throwable throwable);

    void crash(String msg);

    void startMethod();

    void endMethod();
}