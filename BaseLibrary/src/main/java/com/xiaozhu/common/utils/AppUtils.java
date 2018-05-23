package com.xiaozhu.common.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.xiaozhu.common.R;
import com.xiaozhu.common.app.BaseApplication;

import java.io.File;
import java.util.List;

/**
 * @说明 程序公共类
 * @作者 liuyi
 * @时间 2018/4/10 13:53
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class AppUtils {

    /**
     * 判断数据是否为空
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * 判断数据是否为空
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取版本号
     *
     * @return 当前程序版本 1.0
     */
    public static String getVersionName() {
        return getPackageInfo() == null ? null : getPackageInfo().versionName;
    }

    /**
     * 获取版本编号
     *
     * @return 当前版本Code值
     */
    public static int getVersionCode() {
        return getPackageInfo() == null ? 0 : getPackageInfo().versionCode;
    }


    /**
     * 获取当前应用信息类
     *
     * @return 应用信息
     */
    public static PackageInfo getPackageInfo() {
        try {
            PackageManager manager = BaseApplication.getInstance().getmContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseApplication.getInstance().getmContext().getPackageName(), 0);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据包名判断App是否安装
     *
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(String packageName) {
        return getIntentByPackageName(packageName) != null;
    }

    /**
     * 打开指定包名的App
     *
     * @param packageName 包名
     * @return {@code true}: 打开成功<br>{@code false}: 打开失败
     */
    public static boolean openAppByPackageName(String packageName) {
        Intent intent = getIntentByPackageName(packageName);
        if (intent != null) {
            BaseApplication.getInstance().getmContext().startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 根据包名获取意图
     *
     * @param packageName 包名
     * @return 意图
     */
    private static Intent getIntentByPackageName(String packageName) {
        return BaseApplication.getInstance().getmContext().getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取文件 地址
     *
     * @param file 文件
     * @return 返回文件地址
     */
    public static Uri getUri(File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(file);
        } else {
            uri = getFileUri(file);
        }
        return uri;
    }

    /**
     * 获取文件地址SDK > 23以上
     *
     * @param file 文件
     * @return 文件地址
     */
    public static Uri getFileUri(File file) {
        return FileProvider.getUriForFile(BaseApplication.getInstance().getmContext(), BaseApplication.getInstance().getmContext().getResources().getString(R.string.app_package) + ".fileprovider", file);
    }

    /**
     * 获取方法名
     *
     * @param stackMethod
     * @param stackIndex
     * @return
     */
    public static String getMethodNameInfo(String stackMethod, int stackIndex) {
        StackTraceElement stackTraceElement = getStackTraceElementInfo(stackMethod, stackIndex);
        return stackTraceElement != null ? stackTraceElement.getMethodName() : null;
    }

    /**
     * 得到异常所在代码位置信息
     */
    private static StackTraceElement getStackTraceElementInfo(String stackMethod, int stackIndex) {
        StackTraceElement stackTraceElement = null;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        int index = 0;
        int size = traces != null ? traces.length : 0;
        for (int i = 0; i < size; i++) {
            StackTraceElement trace = traces[i];
            if (trace.getMethodName().contains(stackMethod)) {
                index = i + stackIndex;
                break;
            }
        }
        if (index < size) {
            stackTraceElement = traces[index];
        }
        return stackTraceElement;
    }
}
