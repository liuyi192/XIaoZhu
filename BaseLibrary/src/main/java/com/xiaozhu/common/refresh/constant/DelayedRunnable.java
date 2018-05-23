package com.xiaozhu.common.refresh.constant;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 11:01
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DelayedRunnable implements Runnable {
    public long delayMillis;
    private Runnable runnable = null;

    public DelayedRunnable(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.delayMillis = delayMillis;
    }

    @Override
    public void run() {
        try {
            if (runnable != null) {
                runnable.run();
                runnable = null;
            }
        } catch (Throwable e) {
            if (!(e instanceof NoClassDefFoundError)) {
                e.printStackTrace();
            }
        }
    }
}
