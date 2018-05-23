package com.xiaozhu.dome.activity.loading;

import android.view.View;

import com.xiaozhu.common.base.activitys.BaseActivity;
import com.xiaozhu.common.listeners.CountdownTimerListener;
import com.xiaozhu.common.utils.CountdownTimerUtils;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.widget.loading.LoadDialog;
import com.xiaozhu.common.widget.toast.ToastUtils;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/23 9:53
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LoadingActivity extends BaseActivity<LoadingDelegate> implements View.OnClickListener {
    private LoadDialog loadDialog;

    private CountdownTimerUtils countdownTimerUtils;

    @Override
    public Class getDelegateClass() {
        return LoadingDelegate.class;
    }

    @Override
    public void bindEvenListener() {
        viewDelegate.setOnClickListener(this,
                R.id.btnLoading,
                R.id.btnLoadingSuccess,
                R.id.btnLoadingFail,
                R.id.btnToast,
                R.id.btnToastFail
        );

    }

    @Override
    public void business() {
        loadDialog = new LoadDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoading:
                loadDialog.setMsgAndType("加载中...", LoadDialog.ICON_TYPE_LOADING).show();
                initTimer();
                break;
            case R.id.btnLoadingSuccess:
                loadDialog.setMsgAndType("加载成功", LoadDialog.ICON_TYPE_SUCCESS).show();
                initTimer();
                break;
            case R.id.btnLoadingFail:
                loadDialog.setMsgAndType("加载失败", LoadDialog.ICON_TYPE_FAIL).show();
                initTimer();
                break;
            case R.id.btnToast:
                ToastUtils.show("提示成功", 15000, ToastUtils.ICON_TYPE_SUCCESS);
                break;
            case R.id.btnToastFail:
                ToastUtils.show("提示失败", 15000, ToastUtils.ICON_TYPE_FAIL);
                break;
        }
    }

    public void initTimer() {
        //倒计时
        countdownTimerUtils = new CountdownTimerUtils(3);
        countdownTimerUtils.setCountdownTimerListener(new CountdownTimerListener() {
            @Override
            public void onTiming(int second) {
                LogUtils.i("倒计时：" + second);
            }

            @Override
            public void onEnd() {
                if (loadDialog.isShowing()) loadDialog.dismiss();
            }
        });
        countdownTimerUtils.start();
    }
}
