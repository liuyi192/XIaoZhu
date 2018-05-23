package com.xiaozhu.common.log.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.xiaozhu.common.log.Logging;
import com.xiaozhu.common.log.LoggingFileUtils;
import com.xiaozhu.common.umeng.UmengManagement;
import com.xiaozhu.common.utils.LogUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @说明 异常日志输出
 * @作者 liuyi
 * @时间 2018/4/26 10:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CrashExceptionLogger implements CrashHandler.OnCaughtCrashExceptionListener {
    /**
     * 引用程序Context
     **/
    private Context mAppContext;
    /**
     * 用来存储设备信息和异常信息
     */
    private Map<String, String> infos = new HashMap<String, String>();

    public CrashExceptionLogger(Context context) {
        mAppContext = context;
    }

    @Override
    public void onCaughtCrashException(Thread thread, Throwable ex) {
        collectDeviceInfo(mAppContext);
        logCrashInfo(ex);
        OnCrashInfoListener mOnCrashInfoListener = Logging.getLoggingConfig().getOnCrashInfoListener();
        if (mOnCrashInfoListener != null) {
            File file = LoggingFileUtils.getTodayLogFile();
            if (file.exists())
                mOnCrashInfoListener.onUpdateCrashInfo(file);
        }
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx 上下文文本对象
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e("收集包信息时出现错误", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LogUtils.e("当收集崩溃信息时出现错误", e);
            }
        }
    }

    private void logCrashInfo(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }
        infos.clear();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = writer.toString();
        printWriter.close();
        String crashLog = sb.append(result).toString();
        {//友盟统计数据
            UmengManagement.getInstance().sendError(mAppContext, crashLog);
        }
        Logging.crash(crashLog);
    }
}
