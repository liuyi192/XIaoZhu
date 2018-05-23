package com.xiaozhu.common.eventBus;

import de.greenrobot.event.EventBus;

/**
 * @说明 事件监听
 * @作者 liuyi
 * @时间 2018/4/12 10:45
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class EventBusUtils {
    private static EventBusUtils instance;

    public static EventBusUtils getInstance() {
        if (instance == null) {
            synchronized (EventBusUtils.class) {
                if (instance == null) {
                    instance = new EventBusUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 注册监听
     *
     * @param object
     */
    public void registerEventBus(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    /**
     * 注销注册监听
     *
     * @param object
     */
    public void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    /**
     * 发送一个广播
     *
     * @param type 类型编号
     */
    public void sendEventBus(int type) {
        sendEventBus(new EventBusEntity(type, null));
    }

    /**
     * 发送一个广播
     *
     * @param type   类型编号
     * @param object 数据
     */
    public void sendEventBus(int type, Object object) {
        sendEventBus(new EventBusEntity(type, object));
    }

    /**
     * 发送一个广播
     *
     * @param eventBusEntity 数据对象
     */
    public void sendEventBus(EventBusEntity eventBusEntity) {
        EventBus.getDefault().post(eventBusEntity);
    }
}
