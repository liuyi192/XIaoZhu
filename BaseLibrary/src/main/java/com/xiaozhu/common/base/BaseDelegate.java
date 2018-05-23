package com.xiaozhu.common.base;

import android.content.Context;

import com.xiaozhu.common.eventBus.EventBusEntity;
import com.xiaozhu.common.mvp.view.AppDelegate;

/**
 * @说明 基类委托
 * @作者 liuyi
 * @时间 2018/4/12 10:28
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseDelegate extends AppDelegate {
    @Override
    public void business(Context context) {

    }

    @Override
    public void mainEvent(EventBusEntity eventBusEntity) {

    }

    @Override
    public void asyncEvent(EventBusEntity eventBusEntity) {

    }
}
