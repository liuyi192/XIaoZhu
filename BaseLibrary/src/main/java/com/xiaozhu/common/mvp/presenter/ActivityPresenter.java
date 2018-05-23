package com.xiaozhu.common.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiaozhu.common.base.BaseViewInterface;
import com.xiaozhu.common.eventBus.EventBusEntity;
import com.xiaozhu.common.eventBus.EventBusUtils;
import com.xiaozhu.common.mvp.view.IDelegate;
import com.xiaozhu.common.utils.ActivityManger;

import butterknife.ButterKnife;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @说明 层的实现基类
 * @作者 liuyi
 * @时间 2018/4/12 9:22
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity implements BaseViewInterface {
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate = (T) getDelegateClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        //注册监听
        EventBusUtils.getInstance().registerEventBus(this);
        //把当前Activity加入堆栈内
        ActivityManger.getInstance().addActivity(this);
        viewDelegate.setBundle(getIntent().getExtras());
        viewDelegate.initWidget(this);
        viewDelegate.business(this);
        business();
        //绑定事件监听
        bindEvenListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate.onDestroy();
        viewDelegate = null;
        //Activity堆栈去掉当前
        ActivityManger.getInstance().finishActivity();
        //关闭消息监听
        EventBusUtils.getInstance().unregister(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = (T) getDelegateClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
