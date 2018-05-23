package com.xiaozhu.dome.activity.indicator;

import android.os.Bundle;
import android.widget.TextView;

import com.xiaozhu.common.mvp.presenter.FragmentPresenter;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/23 15:17
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class IndicatorFragment extends FragmentPresenter {
    private static final String TITLE = "title";

    public static final IndicatorFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        IndicatorFragment fragment = new IndicatorFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Class getDelegateClass() {
        return IndicatorFragmentDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {
        String title = getArguments().getString(TITLE);
        ((TextView) viewDelegate.getRootView().findViewById(R.id.tvTitle)).setText(title);
    }
}
