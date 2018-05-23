package com.xiaozhu.dome.activity.log;

import android.content.Context;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.log.Logging;
import com.xiaozhu.dome.R;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/26 14:39
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LogDelegate extends BaseDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    public void initWidget(Context context) {
        Logging.startMethod();
        Logging.i("========>>>>>>>>>>>>>>>>   initWidget <<<<<<<<<<<<<<<<<<<=======");

        nullPointerException();

        Logging.endMethod();
    }


    public void nullPointerException() {
        String s = null;
        s.length();
    }
}
