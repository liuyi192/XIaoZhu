package com.xiaozhu.dome.activity.refresh;

import com.xiaozhu.common.base.activitys.BaseRecyclerActivity;

/**
 * @说明 刷新列表实现
 * @作者 liuyi
 * @时间 2018/5/4 15:25
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshListActivity extends BaseRecyclerActivity {
    @Override
    public Class getDelegateClass() {
        return RefreshListDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
