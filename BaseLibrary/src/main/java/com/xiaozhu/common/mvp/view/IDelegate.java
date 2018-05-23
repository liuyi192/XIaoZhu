package com.xiaozhu.common.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhu.common.eventBus.EventBusEntity;

/**
 * @说明 视图层代理的接口协议
 * @作者 liuyi
 * @时间 2018/4/12 9:12
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface IDelegate {

    View getRootView();

    int getRootLayoutId();

    void initWidget(Context context);

    void business(Context context);

    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void setOnClickListener(View.OnClickListener listener, int... ids);

    void mainEvent(EventBusEntity eventBusEntity);

    void asyncEvent(EventBusEntity eventBusEntity);

    void setBundle(Bundle bundle);

    void onDestroy();
}
