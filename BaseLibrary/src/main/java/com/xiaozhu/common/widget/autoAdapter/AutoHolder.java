package com.xiaozhu.common.widget.autoAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * @说明 自动界面持有者
 * @作者 LY
 * @时间 2018/2/6 16:42
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {
    public static final String LISTENER = "OnAutoHolderListener";

    protected Map<String, Object> dataMap;

    public AutoHolder(View itemView, Map<String, Object> dataMap) {
        super(itemView);
        this.dataMap = dataMap;
        //注解
        ButterKnife.bind(this, itemView);
    }

    protected OnAutoHolderListener getOnAutoHolderListener() {
        if (dataMap != null) {
            return (OnAutoHolderListener) dataMap.get(LISTENER);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String key) {
        if (dataMap != null) {
            return (T) dataMap.get(key);
        }
        return null;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public abstract void bind(int position, M bean);
}
