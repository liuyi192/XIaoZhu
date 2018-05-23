package com.xiaozhu.common.download.manger;

import android.content.Context;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/18 9:52
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DownloadBuilder {
    private String url;//下载链接
    private String path;//保存路径
    private String name;//文件名
    private int childTaskCount;//单个任务采用几个线程下载

    private Context context;

    public DownloadBuilder(Context context) {
        this.context = context.getApplicationContext();
    }

    public DownloadBuilder() {
    }

    public DownloadBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DownloadBuilder path(String path) {
        this.path = path;
        return this;
    }

    public DownloadBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DownloadBuilder childTaskCount(int thread) {
        this.childTaskCount = thread;
        return this;
    }

    public DownloadManger build() {
        DownloadManger downloadManger = DownloadManger.getInstance(context);
        downloadManger.init(url, path, name, childTaskCount);
        return downloadManger;
    }
}
