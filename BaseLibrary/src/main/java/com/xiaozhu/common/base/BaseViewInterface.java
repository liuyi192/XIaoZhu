package com.xiaozhu.common.base;

import com.xiaozhu.common.mvp.view.IDelegate;

/**
 * @说明 界面接口基类
 * @作者 liuyi
 * @时间 2018/4/13 14:04
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface BaseViewInterface<T extends IDelegate> {
    /**
     * 得到委托类
     *
     * @return
     */
    Class<T> getDelegateClass();

    /**
     * 绑定监听器
     */
    void bindEvenListener();

    /**
     * 数据处理
     */
    void business();
}
