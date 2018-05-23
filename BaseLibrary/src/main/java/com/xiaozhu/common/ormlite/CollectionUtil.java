package com.xiaozhu.common.ormlite;

import java.util.Collection;
import java.util.List;

/**
 * @说明 集合工具
 * @作者 liuyi
 * @时间 2018/4/28 10:26
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CollectionUtil {
    /**
     * 集合中是否存在指定元素
     *
     * @param value 指定字符
     * @param list  集合
     * @return
     */
    public static boolean existValue(String value, List<String> list) {
        if (list == null || value == null) {
            return false;
        }
        for (String str : list) {
            if (value.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为空集合,集合为null或size=0时返回true.
     *
     * @param collection 集合
     * @return true:null或者0个元素,false:有元素的集合
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
