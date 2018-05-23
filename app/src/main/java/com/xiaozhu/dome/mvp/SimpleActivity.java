package com.xiaozhu.dome.mvp;

import android.view.View;

import com.xiaozhu.common.mvp.presenter.ActivityPresenter;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/12 9:32
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class SimpleActivity extends ActivityPresenter<SimpleDelegate> implements View.OnClickListener {

    @Override
    public Class getDelegateClass() {
        return SimpleDelegate.class;
    }

    @Override
    public void bindEvenListener() {
        viewDelegate.setOnClickListener(this, R.id.tvTitle);
    }

    @Override
    public void business() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitle:
                viewDelegate.bindData("我是点击后的");
                break;
        }
    }
}
