package com.xiaozhu.dome.activity.expandable;

import com.xiaozhu.common.base.activitys.BaseActivity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 9:33
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ExpandableActivity extends BaseActivity<ExpandableDelegate> {
    @Override
    public Class getDelegateClass() {
        return ExpandableDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
