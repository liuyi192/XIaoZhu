package com.xiaozhu.common.widget.autoAdapter;

import java.util.Map;

/**
 * @说明 自动界面包
 * @作者 LY
 * @时间 2018/2/6 16:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class AutoHolderPackage<H extends AutoHolder> {
    private Class<H> holderClass;
    private int holderLayoutRes;
    private Map<String, Object> dataMap;

    public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes) {
        this.holderClass = holderClass;
        this.holderLayoutRes = holderLayoutRes;
    }

    public AutoHolderPackage(Class<H> holderClass, int holderLayoutRes, Map<String, Object> dataMap) {
        this.holderClass = holderClass;
        this.holderLayoutRes = holderLayoutRes;
        this.dataMap = dataMap;
    }

    public Class<H> getHolderClass() {
        return holderClass;
    }

    public void setHolderClass(Class<H> holderClass) {
        this.holderClass = holderClass;
    }

    public int getHolderLayoutRes() {
        return holderLayoutRes;
    }

    public void setHolderLayoutRes(int holderLayoutRes) {
        this.holderLayoutRes = holderLayoutRes;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
