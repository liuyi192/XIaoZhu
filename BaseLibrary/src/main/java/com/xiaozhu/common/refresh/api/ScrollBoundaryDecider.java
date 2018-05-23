package com.xiaozhu.common.refresh.api;

import android.view.View;

/**
 * @说明 滚动边界
 * @作者 liuyi
 * @时间 2018/5/4 10:03
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface ScrollBoundaryDecider {
    /**
     * 根据内容视图状态判断是否可以开始下拉刷新
     *
     * @param content 内容视图
     * @return true 将会触发下拉刷新
     */
    boolean canRefresh(View content);

    /**
     * 根据内容视图状态判断是否可以开始上拉加载
     *
     * @param content 内容视图
     * @return true 将会触发加载更多
     */
    boolean canLoadMore(View content);
}
