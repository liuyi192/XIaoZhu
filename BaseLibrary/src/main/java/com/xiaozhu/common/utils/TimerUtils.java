package com.xiaozhu.common.utils;

import android.os.Handler;
import android.os.Message;

import com.xiaozhu.common.listeners.TimerListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @说明 计时器工具类
 * @作者 liuyi
 * @时间 2018/4/11 10:54
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class TimerUtils {
    private static final int UPDATE_TIME = 1001;
    private Timer timer;
    private TimerTask timerTask;
    private TimerListener timerListener;

    /**
     * 开启计时器
     */
    public void startTimer() {
        clearTimer();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(UPDATE_TIME);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    /**
     * 设置计时器回调监听
     *
     * @param timerListener
     */
    public void setTimerListener(TimerListener timerListener) {
        this.timerListener = timerListener;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TIME:
                    if (timerListener != null) {
                        timerListener.onCallback();
                    }
                    break;
            }
        }
    };

    public void clearTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
