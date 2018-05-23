package com.xiaozhu.common.download;

import android.content.Context;

import com.xiaozhu.common.download.manger.DownloadBuilder;

/**
 * @说明 下载工具类
 * @作者 liuyi
 * @时间 2018/5/18 9:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DownloadUtils {
    /**
     * 下载
     *
     * @param context
     * @return
     */
    public static DownloadBuilder init(Context context) {
        return new DownloadBuilder(context);
    }
}
