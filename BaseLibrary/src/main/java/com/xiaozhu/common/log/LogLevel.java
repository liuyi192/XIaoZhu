package com.xiaozhu.common.log;

/**
 * @说明 日志级别
 * @作者 liuyi
 * @时间 2018/4/26 10:52
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LogLevel {
    /**
     * 输出颜色为黑色的，输出大于或等于VERBOSE日志级别的信息
     **/
    public static final byte V = 0;
    /**
     * 输出颜色是蓝色的，输出大于或等于DEBUG日志级别的信息
     **/
    public static final byte D = 1;
    /**
     * 输出为绿色，输出大于或等于INFO日志级别的信息
     **/
    public static final byte I = 2;
    /****
     * 输出为橙色, 输出大于或等于WARN日志级别的信息
     **/
    public static final byte W = 3;
    /****
     * 输出为红色，仅输出ERROR日志级别的信息.
     */
    public static final byte E = 4;
    /***
     * 只输出ASSERT级别的信息
     */
    public static final byte WTF = 5;

    public static String level2String(int level) {
        String levelString;
        switch (level) {
            case V:
                levelString = "V";
                break;
            case D:
                levelString = "D";
                break;
            case I:
                levelString = "I";
                break;
            case W:
                levelString = "W";
                break;
            case E:
                levelString = "E";
                break;
            case WTF:
                levelString = "WTF";
                break;
            default:
                levelString = "D";
                break;
        }
        return levelString;
    }

    private LogLevel() {
    }
}
