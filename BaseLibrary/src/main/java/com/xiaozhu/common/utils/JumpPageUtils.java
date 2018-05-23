package com.xiaozhu.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * @说明 界面跳转工具类
 * @作者 liuyi
 * @时间 2018/5/21 13:58
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class JumpPageUtils {
    /**
     * 界面跳转
     *
     * @param context
     * @param clas    跳转类
     */
    public static void jumpPage(Context context, Class clas) {
        jumpPage(context, clas, null);
    }

    /**
     * 跳转到下个界面
     *
     * @param context
     * @param clas    类
     * @param bundle  参数
     */
    public static void jumpPage(Context context, Class clas, Bundle bundle) {
        Intent intent = new Intent(context, clas);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 界面跳转
     *
     * @param context
     * @param classPath 跳转类
     */
    public static void jumpPage(Context context, String classPath) {
        jumpPage(context, classPath, null);
    }

    /**
     * 跳转到下个界面
     *
     * @param context
     * @param classPath 类路径
     * @param bundle    参数
     */
    public static void jumpPage(Context context, String classPath, Bundle bundle) {
        if (TextUtils.isEmpty(classPath)) {
            return;
        }
        jumpPage(context, getClass(classPath), bundle);
    }

    /**
     * 获取类
     *
     * @param classPath 类路径
     * @return
     */
    public static Class getClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
