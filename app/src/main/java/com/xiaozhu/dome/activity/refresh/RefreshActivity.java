package com.xiaozhu.dome.activity.refresh;

import com.xiaozhu.common.base.activitys.BaseRecyclerActivity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 11:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshActivity extends BaseRecyclerActivity<RefreshDelegate> {

    @Override
    public Class getDelegateClass() {
        return RefreshDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
