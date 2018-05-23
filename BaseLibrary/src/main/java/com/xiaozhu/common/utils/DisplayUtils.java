package com.xiaozhu.common.utils;

import com.xiaozhu.common.app.BaseApplication;

/**
 * @说明 屏幕密度
 * @作者 liuyi
 * @时间 2018/4/10 12:01
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DisplayUtils {

    /**
     * PX转换成DP
     *
     * @param pxValue px值
     * @return px转换成的dip
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }

    /**
     * DP转换成PX
     *
     * @param dipValue dp值
     * @return dip转换成的px
     */
    public static int dip2px(float dipValue) {
        return (int) (dipValue * getDensity() + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue px值
     * @return 转换后的sp
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / getFontScale() + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue sp值
     * @return 转换后的px
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getFontScale() + 0.5f);
    }

    /**
     * 获取屏幕密度
     *
     * @return 屏幕密度
     */
    public static float getDensity() {
        return BaseApplication.getInstance().getmContext().getResources().getDisplayMetrics().density;
    }

    /**
     * 获取字体屏幕密度
     *
     * @return 字体密度值
     */
    public static float getFontScale() {
        return BaseApplication.getInstance().getmContext().getResources().getDisplayMetrics().scaledDensity;
    }
}
