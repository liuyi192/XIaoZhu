package com.xiaozhu.dome.activity.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.widget.indicator.IndicatorView;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/23 15:03
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class IndicatorDelegate extends BaseDelegate {
    public IndicatorView indicator;
    public ViewPager viewPager;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_indicator;
    }

    @Override
    public void initWidget(Context context) {
        indicator = getView(R.id.indicator);
        viewPager = getView(R.id.viewPager);
    }
}
