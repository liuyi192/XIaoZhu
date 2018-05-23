package com.xiaozhu.dome.activity.progress;

import com.xiaozhu.common.base.activitys.BaseActivity;

/**
 * @说明 圆形进度条
 * @作者 liuyi
 * @时间 2018/4/18 14:11
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ProgressActivity extends BaseActivity<ProgressDelegate> {

    @Override
    public Class getDelegateClass() {
        return ProgressDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
