package com.xiaozhu.common.utils;

import com.xiaozhu.common.listeners.CountdownTimerListener;
import com.xiaozhu.common.listeners.TimerListener;

/**
 * @说明 倒计时工具类
 * @作者 liuyi
 * @时间 2018/4/11 11:05
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CountdownTimerUtils {
    private int maxTime = 0;
    private TimerUtils timerUtils;
    private CountdownTimerListener countdownTimerListener;

    public CountdownTimerUtils(int maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * 开启定时器
     */
    public void start() {
        closeCountdown();
        timerUtils = new TimerUtils();
        timerUtils.setTimerListener(new TimerListener() {
            @Override
            public void onCallback() {
                maxTime--;
                if (countdownTimerListener != null) {
                    if (maxTime <= 0) {
                        countdownTimerListener.onEnd();
                        closeCountdown();
                    } else {
                        countdownTimerListener.onTiming(maxTime);
                    }
                }
            }
        });
        timerUtils.startTimer();
    }

    /**
     * 关闭计时器
     */
    public void closeCountdown() {
        if (timerUtils != null) {
            timerUtils.clearTimer();
            timerUtils = null;
        }
    }

    public void setCountdownTimerListener(CountdownTimerListener countdownTimerListener) {
        this.countdownTimerListener = countdownTimerListener;
    }

}
