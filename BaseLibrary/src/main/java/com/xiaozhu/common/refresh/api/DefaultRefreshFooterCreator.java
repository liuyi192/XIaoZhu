package com.xiaozhu.common.refresh.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @说明 默认Footer创建器
 * @作者 liuyi
 * @时间 2018/5/4 10:00
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);
}
