package com.xiaozhu.common.widget.autoAdapter;

/**
 * @说明 自动列表包
 * @作者 LY
 * @时间 2018/2/6 16:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class AutoPackage {
    private int type;
    private Object autoPackage;
    private int spanSize;

    public AutoPackage(int type, Object autoPackage) {
        this.type = type;
        this.autoPackage = autoPackage;
    }

    public AutoPackage(int type, Object autoPackage, int spanSize) {
        this.type = type;
        this.autoPackage = autoPackage;
        this.spanSize = spanSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public Object getAutoPackage() {
        return autoPackage;
    }

    public void setAutoPackage(Object autoPackage) {
        this.autoPackage = autoPackage;
    }
}
