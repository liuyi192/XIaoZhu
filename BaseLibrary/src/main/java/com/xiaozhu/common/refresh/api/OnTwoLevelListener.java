package com.xiaozhu.common.refresh.api;

import android.support.annotation.NonNull;

/**
 * @说明 二级刷新监听器
 * @作者 liuyi
 * @时间 2018/5/4 10:00
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface OnTwoLevelListener {
    /**
     * 二级刷新触发
     *
     * @param refreshLayout 刷新布局
     * @return true 将会展开二楼状态 false 关闭刷新
     */
    boolean onTwoLevel(@NonNull RefreshLayout refreshLayout);
}
