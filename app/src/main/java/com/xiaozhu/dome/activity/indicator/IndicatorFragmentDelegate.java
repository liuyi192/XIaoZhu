package com.xiaozhu.dome.activity.indicator;

import android.content.Context;
import android.widget.TextView;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/23 15:24
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class IndicatorFragmentDelegate extends BaseDelegate {
    TextView tvTitle;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_indicator;
    }

    @Override
    public void initWidget(Context context) {
        tvTitle = getView(R.id.tvTitle);
    }
}
