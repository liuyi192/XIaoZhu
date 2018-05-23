package com.xiaozhu.common.refresh.listener;

import android.support.annotation.NonNull;

import com.xiaozhu.common.refresh.api.RefreshLayout;
import com.xiaozhu.common.refresh.constant.RefreshState;

/**
 * @说明 刷新状态改变监听器
 * @作者 liuyi
 * @时间 2018/5/4 10:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface OnStateChangedListener {
    /**
     * 状态改变事件
     *
     * @param refreshLayout RefreshLayout
     * @param oldState      改变之前的状态
     * @param newState      改变之后的状态
     */
    void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState);
}
