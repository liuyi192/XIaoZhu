package com.xiaozhu.common.download.callback;

import java.io.File;

/**
 * @说明 下载回调
 * @作者 liuyi
 * @时间 2018/5/18 9:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface DownloadCallback {
    /**
     * 开始
     */
    void onStart(long currentSize, long totalSize, float progress);

    /**
     * 下载中
     *
     * @param currentSize
     * @param totalSize
     * @param progress
     */
    void onProgress(long currentSize, long totalSize, float progress);

    /**
     * 暂停
     */
    void onPause();

    /**
     * 取消
     */
    void onCancel();

    /**
     * 完成
     *
     * @param file
     */
    void onFinish(File file);

    /**
     * 等待下载
     */
    void onWait();

    /**
     * 出错
     *
     * @param error
     */
    void onError(String error);

}
