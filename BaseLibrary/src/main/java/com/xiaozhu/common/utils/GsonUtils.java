package com.xiaozhu.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @说明 Json处理工具类
 * @作者 liuyi
 * @时间 2018/4/12 15:15
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class GsonUtils {
    /**
     * json转换成对象
     *
     * @param json  json数据
     * @param clazz 对象
     * @return 转换的对象
     */
    public static <T> T jsonToEntity(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * JsonArray转换为List数据
     *
     * @param json  json数据
     * @param clazz 对象
     * @return 列表对象
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        Type type = new TypeToken<List<JsonObject>>() {
        }.getType();
        List<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        List<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     * 对象转换成JSON类型
     *
     * @param bean 对象
     * @return json数据
     */
    public static String beanToJson(Object bean) {
        Gson gson = new Gson();
        return gson.toJson(bean);
    }
}
