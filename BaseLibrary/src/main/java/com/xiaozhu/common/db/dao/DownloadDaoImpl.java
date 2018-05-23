package com.xiaozhu.common.db.dao;

import android.content.Context;

import com.xiaozhu.common.download.data.Consts;
import com.xiaozhu.common.download.data.DownloadData;
import com.xiaozhu.common.ormlite.OrmLiteDao;
import com.xiaozhu.common.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/18 10:28
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DownloadDaoImpl implements DownloadDao {
    private static DownloadDaoImpl mInstance;
    private static Context mContext;
    private OrmLiteDao dao;

    public DownloadDaoImpl(Context context) {
        dao = new OrmLiteDao(context, DownloadData.class);
    }

    public static DownloadDaoImpl getInstance(final Context context) {
        mContext = context;
        if (mInstance == null) {
            synchronized (DownloadDaoImpl.class) {
                if (mInstance == null) {
                    mInstance = new DownloadDaoImpl(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void initDownload(DownloadData downloadData) {
        boolean flag = dao.insert(downloadData);
        if (flag) {
            LogUtils.i("保存成功");
        } else {
            LogUtils.i("保存失败");
        }
    }

    @Override
    public void initDownloads(List<DownloadData> datas) {
        for (DownloadData data : datas) {
            initDownload(data);
        }
    }

    @Override
    public DownloadData getDownload(String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        return (DownloadData) dao.queryForFirst(map);
    }

    @Override
    public List<DownloadData> getAll() {
        return dao.queryForAll();
    }

    @Override
    public void updateProgress(int currentSize, float percentage, int status, String url) {
        DownloadData downloadData = getDownload(url);
        if (downloadData != null) {
            if (status != Consts.PROGRESS) {
                downloadData.setCurrentLength(currentSize);
                downloadData.setPercentage(percentage);
            }
            downloadData.setStatus(status);
            dao.update(downloadData);
        }
    }

    @Override
    public void deleteData(String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        dao.deleteByColumnName(map);
    }
}
