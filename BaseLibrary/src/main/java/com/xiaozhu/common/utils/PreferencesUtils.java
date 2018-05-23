package com.xiaozhu.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaozhu.common.app.BaseApplication;

import java.util.Map;

/**
 * @说明 SP数据存储
 * @作者 liuyi
 * @时间 2018/4/10 11:10
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class PreferencesUtils {

    /**
     * 获取数据存储
     *
     * @return 存储对象
     */
    public static SharedPreferences getSp() {
        Context mContext = BaseApplication.getInstance().getmContext();
        return mContext.getSharedPreferences(mContext.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     *
     * @param key    名称
     * @param object 内容
     */
    public static void saveData(String key, Object object) {
        SharedPreferences.Editor editor = getSp().edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object != null) {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 根据名称获取对象数据
     *
     * @param key           名称
     * @param defaultObject 默认数据
     * @return 数据
     */
    public static Object getData(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return getSp().getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return getSp().getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return getSp().getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return getSp().getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return getSp().getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 根据名称删除数据
     *
     * @param key 名称
     */
    public static void removeKeyData(String key) {
        getSp().edit().remove(key).commit();
    }

    /**
     * 删除所有数据
     */
    public static void clearData() {
        getSp().edit().clear().commit();
    }


    /**
     * 查询某个key是否已经存在
     *
     * @param key 名称
     * @return {@code true}: 数据存在<br>{@code false}:  数据不存在
     */
    public static boolean contains(String key) {
        return getSp().contains(key);
    }


    /**
     * 返回所有的键值对
     *
     * @return 存储的数据对象
     */
    public static Map<String, ?> getAll() {
        return getSp().getAll();
    }
}
