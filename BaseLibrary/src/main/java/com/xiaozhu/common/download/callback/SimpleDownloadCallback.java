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
public abstract class SimpleDownloadCallback implements DownloadCallback {

    @Override
    public void onStart(long currentSize, long totalSize, float progress) {

    }

    @Override
    public void onProgress(long currentSize, long totalSize, float progress) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFinish(File file) {

    }

    @Override
    public void onWait() {

    }

    @Override
    public void onError(String error) {

    }
}
