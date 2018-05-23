package com.xiaozhu.common.db;

import android.content.Context;

import com.xiaozhu.common.R;
import com.xiaozhu.common.download.data.DownloadData;
import com.xiaozhu.common.ormlite.DatabaseManagerWrapper;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/18 10:12
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DatabaseManager extends DatabaseManagerWrapper {
    private static volatile DatabaseManager instance;
    private Context mContext;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        DatabaseManager inst = instance;
        if (inst == null) {
            synchronized (DatabaseManager.class) {
                inst = instance;
                if (inst == null) {
                    inst = new DatabaseManager();
                    instance = inst;
                }
            }
        }
        return inst;
    }

    public void init(Context context) {
        this.mContext = context;
        super.init(context, mContext.getResources().getString(R.string.databaseName), mContext.getResources().getInteger(R.integer.databaseVersion));
        addTable(DownloadData.class);
    }

}
