package com.xiaozhu.dome.activity.http;

import com.xiaozhu.common.base.activitys.BaseActivity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/24 14:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class HttpActivity extends BaseActivity {

    @Override
    public Class getDelegateClass() {
        return HttpDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
