package com.xiaozhu.dome.activity.title;

import android.content.Context;
import android.view.View;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.widget.custom.TitleBarView;
import com.xiaozhu.dome.R;

import butterknife.Bind;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/3 9:31
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class TitleBarDelegate extends BaseDelegate {
    @Bind(R.id.titleBar)
    TitleBarView titleBar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_title_bar;
    }

    @Override
    public void initWidget(Context context) {
        titleBar.setTitle("测试标题");
        titleBar.setBtnIvLeft(R.mipmap.ic_back);
        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        }, R.id.btnIvLeft);
    }
}
