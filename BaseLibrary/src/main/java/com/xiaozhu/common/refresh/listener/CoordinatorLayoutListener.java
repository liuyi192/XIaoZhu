package com.xiaozhu.common.refresh.listener;

/**
 * @说明 协调布局侦听器
 * @作者 liuyi
 * @时间 2018/5/4 10:11
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface CoordinatorLayoutListener {
    void onCoordinatorUpdate(boolean enableRefresh, boolean enableLoadMore);
}
