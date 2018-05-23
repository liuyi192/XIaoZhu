package com.xiaozhu.dome.activity.list;

import com.xiaozhu.common.base.activitys.BaseRecyclerActivity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/13 10:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ListDataActivity extends BaseRecyclerActivity<RecyclerDelegate> {

    @Override
    public Class getDelegateClass() {
        return RecyclerDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {
    }
}
