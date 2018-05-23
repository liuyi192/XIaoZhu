package com.xiaozhu.common.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.xiaozhu.common.R;
import com.xiaozhu.common.db.DatabaseManager;
import com.xiaozhu.common.log.LogLevel;
import com.xiaozhu.common.log.Logging;
import com.xiaozhu.common.log.LoggingConfiguration;
import com.xiaozhu.common.log.crash.OnCrashInfoListener;
import com.xiaozhu.common.umeng.UmengManagement;
import com.xiaozhu.common.utils.FileManagerUtils;

import java.io.File;

/**
 * @说明 程序入口
 * @作者 liuyi
 * @时间 2018/4/10 10:32
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private Context mContext;
    protected DatabaseManager databaseManager;

    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                if (instance == null) {
                    instance = new BaseApplication();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        instance = this;
        mContext = this;
        //设置程序文件夹名称
        FileManagerUtils.getInstance().setFolderName(getResources().getString(R.string.file_fold));
        //初始化日志文件
        initLog();
        //友盟统计
        UmengManagement.getInstance().initUmeng(this, getResources().getString(R.string.umengAppKey), getResources().getString(R.string.umengChannel));
        //初始化数据库表
        databaseManager = DatabaseManager.getInstance();
        databaseManager.init(this);
    }

    /**
     * 获取全文上下文对象
     *
     * @return
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * 初始化日志
     */
    public void initLog() {
        LoggingConfiguration.Builder builder = new LoggingConfiguration.Builder(this)
                .setConsoleLogLevel(LogLevel.D)
                .setFileLogLevel(LogLevel.D)
                .setCrashHandlerOpen(true)
                .setOriginalHandler(Thread.getDefaultUncaughtExceptionHandler())
                .setOnCrashInfoListener(new OnCrashInfoListener() {
                    @Override
                    public void onUpdateCrashInfo(File file) {

                    }
                })
                .setFileLogRetentionPeriod(7)
                .setFileLogRootPath(FileManagerUtils.getInstance().getLogFolder());
        Logging.init(this, builder.build());
    }

}
