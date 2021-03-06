package com.xiaozhu.common.log.crash;

/**
 * @说明 异常日志
 * @作者 liuyi
 * @时间 2018/4/26 10:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * 第三方UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler originalHandler;
    /**
     * 当crash发生后的接口回调
     **/
    private OnCaughtCrashExceptionListener mCaughtCrashExceptionListener;

    /**
     * 当捕获到Crash异常后会通过该接口回调
     */
    public interface OnCaughtCrashExceptionListener {
        void onCaughtCrashException(Thread thread, Throwable ex);
    }

    public void setCaughtCrashExceptionListener(OnCaughtCrashExceptionListener mCaughtCrashExceptionListener) {
        this.mCaughtCrashExceptionListener = mCaughtCrashExceptionListener;
    }

    public void init() {
        init(null);
    }

    /**
     * 初始化
     *
     * @param mUncaughtExceptionHandler 第三方UncaughtExceptionHandler处理
     */
    public void init(Thread.UncaughtExceptionHandler mUncaughtExceptionHandler) {
        originalHandler = mUncaughtExceptionHandler;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mCaughtCrashExceptionListener != null) {
            mCaughtCrashExceptionListener.onCaughtCrashException(thread, ex);
        }
        if (originalHandler != null) {
            originalHandler.uncaughtException(thread, ex);
        }
        mDefaultHandler.uncaughtException(thread, ex);
    }
}
