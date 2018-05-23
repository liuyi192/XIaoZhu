package com.xiaozhu.common.eventBus;

import com.xiaozhu.common.entity.BaseEntity;

/**
 * @说明 事件监听工具对象
 * @作者 liuyi
 * @时间 2018/4/12 10:49
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class EventBusEntity extends BaseEntity {
    /**
     * 类型
     */
    private int type;
    /**
     * 数据
     */
    private Object object;

    public EventBusEntity() {
    }

    public EventBusEntity(int type) {
        this.type = type;
        this.object = "";
    }

    public EventBusEntity(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
