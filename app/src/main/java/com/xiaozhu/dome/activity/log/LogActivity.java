package com.xiaozhu.dome.activity.log;

import com.xiaozhu.common.base.activitys.BaseActivity;

/**
 * @说明 日志工具类
 * @作者 liuyi
 * @时间 2018/4/26 14:38
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LogActivity extends BaseActivity<LogDelegate> {
    @Override
    public Class getDelegateClass() {
        return LogDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {

    }
}
