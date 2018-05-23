package com.xiaozhu.common.refresh.impl;

import android.graphics.PointF;
import android.view.View;

import com.xiaozhu.common.refresh.api.ScrollBoundaryDecider;
import com.xiaozhu.common.utils.ScrollBoundaryUtil;

/**
 * @说明 滚动边界
 * @作者 liuyi
 * @时间 2018/5/4 10:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ScrollBoundaryDeciderAdapter implements ScrollBoundaryDecider {
    public PointF mActionEvent;
    public ScrollBoundaryDecider boundary;
    public boolean mEnableLoadMoreWhenContentNotFull = true;

    @Override
    public boolean canRefresh(View content) {
        if (boundary != null) {
            return boundary.canRefresh(content);
        }
        return ScrollBoundaryUtil.canRefresh(content, mActionEvent);
    }

    @Override
    public boolean canLoadMore(View content) {
        if (boundary != null) {
            return boundary.canLoadMore(content);
        }
        return ScrollBoundaryUtil.canLoadMore(content, mActionEvent, mEnableLoadMoreWhenContentNotFull);
    }
}
