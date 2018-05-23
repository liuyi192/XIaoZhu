package com.xiaozhu.dome.activity.web;

import com.xiaozhu.common.base.activitys.BaseWebViewActivity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/27 15:47
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class WebViewActivity extends BaseWebViewActivity<WebViewDelegate> {
    @Override
    public Class getDelegateClass() {
        return WebViewDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {
        ((WebViewDelegate) viewDelegate).loadUrl("http://www.baidu.com");
    }
}
