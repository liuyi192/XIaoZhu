package com.xiaozhu.common.widget.banner.interfaces;

/**
 * @说明 提示视图委托
 * @作者 liuyi
 * @时间 2018/4/13 14:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface HintViewDelegate {
    void setCurrentPosition(int position, HintView hintView);

    void initView(int length, int gravity, HintView hintView);
}
