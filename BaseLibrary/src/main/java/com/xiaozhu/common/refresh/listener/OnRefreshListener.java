package com.xiaozhu.common.refresh.listener;

import android.support.annotation.NonNull;

import com.xiaozhu.common.refresh.api.RefreshLayout;

/**
 * @说明 刷新监听器
 * @作者 liuyi
 * @时间 2018/5/4 10:13
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
