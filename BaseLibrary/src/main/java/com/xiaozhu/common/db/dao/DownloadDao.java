package com.xiaozhu.common.db.dao;

import com.xiaozhu.common.download.data.DownloadData;

import java.util.List;

/**
 * @说明 下载Dao
 * @作者 liuyi
 * @时间 2018/5/18 10:23
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface DownloadDao {
    void initDownload(DownloadData downloadData);

    void initDownloads(List<DownloadData> datas);

    DownloadData getDownload(String url);

    List<DownloadData> getAll();

    void updateProgress(int currentSize, float percentage, int status, String url);

    void deleteData(String url);
}
