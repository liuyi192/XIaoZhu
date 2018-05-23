package com.xiaozhu.common.refresh.impl;

import android.view.View;

import com.xiaozhu.common.refresh.api.RefreshHeader;
import com.xiaozhu.common.refresh.internal.InternalAbstract;

/**
 * @说明 刷新头部包装
 * @作者 liuyi
 * @时间 2018/5/4 10:49
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshHeaderWrapper extends InternalAbstract implements RefreshHeader {
    public RefreshHeaderWrapper(View wrapper) {
        super(wrapper);
    }
}
