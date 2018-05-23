package com.xiaozhu.common.refresh.impl;

import android.view.View;

import com.xiaozhu.common.refresh.api.RefreshFooter;
import com.xiaozhu.common.refresh.internal.InternalAbstract;

/**
 * @说明 刷新底部包装
 * @作者 liuyi
 * @时间 2018/5/4 10:48
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshFooterWrapper extends InternalAbstract implements RefreshFooter {
    public RefreshFooterWrapper(View wrapper) {
        super(wrapper);
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return mWrapperView instanceof RefreshFooter && ((RefreshFooter) mWrapperView).setNoMoreData(noMoreData);
    }
}
