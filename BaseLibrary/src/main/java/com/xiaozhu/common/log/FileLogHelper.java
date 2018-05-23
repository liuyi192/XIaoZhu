package com.xiaozhu.common.log;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import android.os.StatFs;
import android.util.Log;

import com.xiaozhu.common.utils.TimeDateUtil;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/26 13:59
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class FileLogHelper {
    private static final int LOG_CACHE_POLL_SIZE = 20;
    private List<String> logCache = null;
    private ExecutorService mExecutorService;
    private ReentrantLock mReentrantLock;
    private boolean released = false;
    private static FileLogHelper INSTANCE = null;

    public FileLogHelper() {
        logCache = new ArrayList<>(LOG_CACHE_POLL_SIZE);
        mExecutorService = Executors.newSingleThreadExecutor();
        mReentrantLock = new ReentrantLock();
        deleteExpireFile();
    }

    public static FileLogHelper getInstance() {
        if (null == INSTANCE) {
            synchronized (FileLogHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = new FileLogHelper();
                }
            }
        }
        return INSTANCE;
    }

    public void logToFile(String log, Throwable e, int logLevel) {
        logToFile(log, e, logLevel, false);
    }

    public void logToFile(String log, Throwable e, int logLevel, boolean isForceSave) {
        if (released) {
            return;
        }
        String logMsg = formatLog(log, e);
        addLogToCache(logMsg);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                saveToFile();
            }
        };
        if (isForceSave) {
            runnable.run();
        } else if (getCacheSize() >= LOG_CACHE_POLL_SIZE) {
            mExecutorService.execute(runnable);
        }
    }

    private void saveToFile() {
        String logFilePath = initLogFile();
        if (null != logFilePath && logFilePath.trim().length() > 0) {
            BufferedWriter bw = null;
            mReentrantLock.lock();
            try {
                bw = new BufferedWriter(new FileWriter(logFilePath, true), 1024);
                for (String log : logCache) {
                    bw.write(log);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
                closeQuietly(bw);
            }
        }
        clearCache();
    }

    /**
     * 初始化Log文件路径
     *
     * @return String Log文件路径
     */
    private String initLogFile() {
        String result = null;
        try {
            File fileDir = LoggingFileUtils.getLogDirFile();
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = LoggingFileUtils.getTodayLogFile();
            if (!file.exists()) {
                file.createNewFile();
            }
            result = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void destroy() {
        released = true;
        if (null != mExecutorService && !mExecutorService.isShutdown()) {
            mExecutorService.shutdown();
        }
        mExecutorService = null;
        clearCache();
        if (mReentrantLock.isLocked())
            mReentrantLock.unlock();
    }

    private void addLogToCache(String log) {
        if (null == logCache || log == null) {
            return;
        }
        mReentrantLock.lock();
        try {
            logCache.add(log);
        } finally {
            mReentrantLock.unlock();
        }
    }

    private void clearCache() {
        mReentrantLock.lock();
        try {
            logCache.clear();
            if (released) {
                logCache = null;
            }
        } finally {
            mReentrantLock.unlock();
        }
    }

    private int getCacheSize() {
        if (null == logCache) {
            return 0;
        }
        mReentrantLock.lock();
        try {
            return logCache.size();
        } finally {
            mReentrantLock.unlock();
        }
    }

    private void deleteExpireFile() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                LoggingConfiguration loggingConfiguration = Logging.getLoggingConfig();
                if (loggingConfiguration == null)
                    return;
                File fileDir = LoggingFileUtils.getLogDirFile();
                LoggingFileUtils.delOutDateFile(fileDir, loggingConfiguration.getFileLogRetentionPeriod());
                boolean logFileDirSpaceMax = LoggingFileUtils.logFileDirSpaceMax(fileDir, loggingConfiguration.getFileLogDiskMemorySize());
                if (logFileDirSpaceMax) {
                    LoggingFileUtils.delAllFileByDir(fileDir);
                }
            }
        });
    }

    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String formatLog(String message, Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        if (message != null) {
            stringBuilder.append(message);
        }
        if (null != e) {
            stringBuilder.append("\n");
            stringBuilder.append(Log.getStackTraceString(e));
        }
        return stringBuilder.toString();
    }
}
