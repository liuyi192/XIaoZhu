package com.xiaozhu.dome.activity.loading;

import android.content.Context;
import android.widget.TextView;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.dome.R;

/**
 * @说明 加载对话框
 * @作者 liuyi
 * @时间 2018/4/23 9:54
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LoadingDelegate extends BaseDelegate {
    private TextView btnLoading;
    private TextView btnLoadingSuccess;
    private TextView btnLoadingFail;
    private TextView btnToast;
    private TextView btnToastFail;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initWidget(Context context) {
        btnLoading = getView(R.id.btnLoading);
        btnLoadingSuccess = getView(R.id.btnLoadingSuccess);
        btnLoadingFail = getView(R.id.btnLoadingFail);
        btnToast = getView(R.id.btnToast);
        btnToastFail = getView(R.id.btnToastFail);
    }
}
