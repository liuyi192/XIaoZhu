package com.xiaozhu.common.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhu.common.base.BaseViewInterface;
import com.xiaozhu.common.eventBus.EventBusEntity;
import com.xiaozhu.common.eventBus.EventBusUtils;
import com.xiaozhu.common.mvp.view.IDelegate;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @说明 层的实现基类
 * @作者 liuyi
 * @时间 2018/4/12 10:15
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class FragmentPresenter<T extends IDelegate> extends Fragment implements BaseViewInterface {
    public T viewDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = (T) getDelegateClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注册监听
        EventBusUtils.getInstance().registerEventBus(this);
        viewDelegate.initWidget(getActivity());
        viewDelegate.business(getActivity());
        viewDelegate.setBundle(getArguments());
        bindEvenListener();
        business();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewDelegate.create(inflater, container, savedInstanceState);
        return viewDelegate.getRootView();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = (T) getDelegateClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
        //关闭消息监听
        EventBusUtils.getInstance().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEventBus(EventBusEntity eventBusEntity) {
        viewDelegate.mainEvent(eventBusEntity);
    }

    @Subscribe(threadMode = ThreadMode.Async)
    public void onAsyncEventBus(EventBusEntity eventBusEntity) {
        viewDelegate.asyncEvent(eventBusEntity);
    }

}
