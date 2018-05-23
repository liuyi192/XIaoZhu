package com.xiaozhu.common.listeners;

/**
 * @说明 倒计时工具类
 * @作者 liuyi
 * @时间 2018/4/11 11:08
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface CountdownTimerListener {
    /**
     * 倒计时中
     *
     * @param second 剩余时间
     */
    void onTiming(int second);

    /**
     * 倒计时结束
     */
    void onEnd();
}
