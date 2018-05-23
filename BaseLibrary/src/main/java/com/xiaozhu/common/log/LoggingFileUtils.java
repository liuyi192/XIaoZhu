package com.xiaozhu.common.log;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StatFs;

import com.xiaozhu.common.utils.FileManagerUtils;
import com.xiaozhu.common.utils.TimeDateUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/26 11:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LoggingFileUtils {
    public static File getTodayLogFile() {
        LoggingConfiguration loggingConfiguration = Logging.getLoggingConfig();
        File todayLogFile = null;
        if (loggingConfiguration != null) {
            todayLogFile = new File(getLogDirFile(), TimeDateUtil.getToDate() + loggingConfiguration.getLogFileSuffix());
        }
        return todayLogFile;
    }

    public static File getLogDirFile() {
        LoggingConfiguration xLogConfiguration = Logging.getLoggingConfig();
        if (xLogConfiguration == null)
            return null;
        String fileRootPath = xLogConfiguration.getFileLogRootPath();
        if (fileRootPath == null || fileRootPath.length() == 0)
            fileRootPath = FileManagerUtils.getInstance().getAppPath();
        String fileDirName = xLogConfiguration.getFileLogDirName();
        return new File(fileRootPath, fileDirName);
    }

    @SuppressLint("NewApi")
    public static boolean logFileDirSpaceMax(File dir, long max) {
        boolean result = false;
        try {
            final StatFs stats = new StatFs(dir.getPath());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                result = stats.getBlockSizeLong() >= max;
            } else {
                result = stats.getBlockSize() >= max;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void delAllFileByDir(File dir) {
        if (null == dir || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        if (null == files || files.length == 0) {
            return;
        }
        for (File file : files) {
            file.delete();
        }
    }

    /**
     * 删除过期日志
     *
     * @param fileDir  文件地址
     * @param saveDays 保存天数
     */
    public static void delOutDateFile(File fileDir, int saveDays) {
        if (null == fileDir || !fileDir.isDirectory() || saveDays <= 0) {
            return;
        }
        File[] files = fileDir.listFiles();
        if (null == files || files.length == 0) {
            return;
        }
        for (File file : files) {
            String dateString = file.getName();
            if (canDeleteSDLog(dateString, saveDays)) {
                file.delete();
            }
        }
    }

    /**
     * 判断sdcard上的日志文件是否可以删除
     *
     * @param createDateStr 日期串
     * @return true表示可删除，false表示否
     */
    private static boolean canDeleteSDLog(String createDateStr, int saveDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1 * saveDays);
        Date expiredDate = calendar.getTime();
        Date createDate = new Date(string2Millis(createDateStr, TimeDateUtil.DEFAULT_DATE_PATTERN, false));
        return createDate.before(expiredDate);
    }

    public static long string2Millis(String dateString, String timeType, boolean isTimeZone) {
        long result = 0;
        DateFormat dateFormat = new SimpleDateFormat(timeType, Locale.US);
        if (isTimeZone)
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = dateFormat.parse(dateString);
            result = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
