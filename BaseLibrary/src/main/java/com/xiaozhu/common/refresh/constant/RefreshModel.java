package com.xiaozhu.common.refresh.constant;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 14:58
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public enum RefreshModel {
    DISABLED(0),//默认无刷新
    PULL_FROM_START(1),//刷新头部
    PULL_FROM_END(2),//刷新底部
    BOTH(3);//头部底部刷新
    private int type;

    RefreshModel(int type) {
        this.type = type;
    }

    public static RefreshModel getModel(int type) {
        for (RefreshModel c : RefreshModel.values()) {
            if (c.getType() == type) {
                return c;
            }
        }
        return DISABLED;
    }

    public int getType() {
        return type;
    }
}
