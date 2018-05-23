package com.xiaozhu.common.utils;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.xiaozhu.common.app.BaseApplication;

/**
 * @说明 字符串工具类
 * @作者 liuyi
 * @时间 2018/4/10 11:29
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class StringUtils {

    /**
     * 数据是否为空
     *
     * @param data 需要校验的数据
     * @return {@code true}: 数据为空<br>{@code false}: 数据不为空
     */
    public static boolean isEmpty(String data) {
        return TextUtils.isEmpty(data);
    }

    /**
     * 资源转换成String
     *
     * @param resId 资源文件
     * @return string.xml里面资源转换成String
     */
    public static String getString(@StringRes int resId) {
        return BaseApplication.getInstance().getString(resId);
    }

    /**
     * 资源转换成String
     *
     * @param resId      资源文件
     * @param formatArgs 资源拼接
     * @return string.xml里面资源转换成String 占位数据添加
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return BaseApplication.getInstance().getString(resId, formatArgs);
    }
}
