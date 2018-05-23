package com.xiaozhu.common.download.manger;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xiaozhu.common.db.dao.DownloadDaoImpl;
import com.xiaozhu.common.download.callback.DownloadCallback;
import com.xiaozhu.common.download.data.Consts;
import com.xiaozhu.common.download.data.DownloadData;

import java.io.File;

/**
 * @说明 下载进度
 * @作者 liuyi
 * @时间 2018/5/18 10:01
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DownloadProgressHandler {
    private String url;
    private String path;
    private String name;
    private int childTaskCount;
    private Context context;
    private DownloadCallback downloadCallback;
    private DownloadData downloadData;
    private FileTask fileTask;
    private int mCurrentState = Consts.NONE;
    //是否支持断点续传
    private boolean isSupportRange;
    //重新开始下载需要先进行取消操作
    private boolean isNeedRestart;
    //记录已经下载的大小
    private int currentLength = 0;
    //记录文件总大小
    private int totalLength = 0;
    //记录已经暂停或取消的线程数
    private int tempChildTaskCount = 0;
    private long lastProgressTime;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int mLastSate = mCurrentState;
            mCurrentState = msg.what;
            downloadData.setStatus(mCurrentState);
            switch (mCurrentState) {
                case Consts.START:
                    Bundle bundle = msg.getData();
                    totalLength = bundle.getInt("totalLength");
                    currentLength = bundle.getInt("currentLength");
                    String lastModify = bundle.getString("lastModify");
                    isSupportRange = bundle.getBoolean("isSupportRange");
                    if (!isSupportRange) {
                        childTaskCount = 1;
                    } else if (currentLength == 0) {
                        DownloadDaoImpl.getInstance(context).initDownload(new DownloadData(url, path, childTaskCount, name, currentLength, totalLength, lastModify, System.currentTimeMillis()));
                    }
                    if (downloadCallback != null) {
                        downloadCallback.onStart(currentLength, totalLength, FileUtils.getPercentage(currentLength, totalLength));
                    }
                    break;
                case Consts.PROGRESS:
                    synchronized (this) {
                        currentLength += msg.arg1;
                        downloadData.setPercentage(FileUtils.getPercentage(currentLength, totalLength));
                        if (downloadCallback != null && (System.currentTimeMillis() - lastProgressTime >= 20 || currentLength == totalLength)) {
                            downloadCallback.onProgress(currentLength, totalLength, FileUtils.getPercentage(currentLength, totalLength));
                            lastProgressTime = System.currentTimeMillis();
                        }
                        if (currentLength == totalLength) {
                            sendEmptyMessage(Consts.FINISH);
                        }
                    }
                    break;
                case Consts.CANCEL:
                    synchronized (this) {
                        tempChildTaskCount++;
                        if (tempChildTaskCount == childTaskCount || mLastSate == Consts.PAUSE || mLastSate == Consts.ERROR) {
                            tempChildTaskCount = 0;
                            if (downloadCallback != null) {
                                downloadCallback.onProgress(0, totalLength, 0);
                            }
                            currentLength = 0;
                            if (isSupportRange) {
                                DownloadDaoImpl.getInstance(context).deleteData(url);
                                FileUtils.deleteFile(new File(path, name + ".temp"));
                            }
                            FileUtils.deleteFile(new File(path, name));
                            if (downloadCallback != null) {
                                downloadCallback.onCancel();
                            }
                            if (isNeedRestart) {
                                isNeedRestart = false;
                                DownloadManger.getInstance(context).innerRestart(url);
                            }
                        }
                    }
                    break;
                case Consts.PAUSE:
                    synchronized (this) {
                        if (isSupportRange) {
                            DownloadDaoImpl.getInstance(context).updateProgress(currentLength, FileUtils.getPercentage(currentLength, totalLength), Consts.PAUSE, url);
                        }
                        tempChildTaskCount++;
                        if (tempChildTaskCount == childTaskCount) {
                            if (downloadCallback != null) {
                                downloadCallback.onPause();
                            }
                            tempChildTaskCount = 0;
                        }
                    }
                    break;
                case Consts.FINISH:
                    if (isSupportRange) {
                        FileUtils.deleteFile(new File(path, name + ".temp"));
                        DownloadDaoImpl.getInstance(context).deleteData(url);
                    }
                    if (downloadCallback != null) {
                        downloadCallback.onFinish(new File(path, name));
                    }
                    break;
                case Consts.DESTROY:
                    synchronized (this) {
                        if (isSupportRange) {
                            DownloadDaoImpl.getInstance(context).updateProgress(currentLength, FileUtils.getPercentage(currentLength, totalLength), Consts.DESTROY, url);
                        }
                    }
                    break;
                case Consts.ERROR:
                    if (isSupportRange) {
                        DownloadDaoImpl.getInstance(context).updateProgress(currentLength, FileUtils.getPercentage(currentLength, totalLength), Consts.ERROR, url);
                    }
                    if (downloadCallback != null) {
                        downloadCallback.onError((String) msg.obj);
                    }
                    break;
            }
        }
    };

    public DownloadProgressHandler(Context context, DownloadData downloadData, DownloadCallback downloadCallback) {
        this.context = context;
        this.downloadCallback = downloadCallback;
        this.url = downloadData.getUrl();
        this.path = downloadData.getPath();
        this.name = downloadData.getName();
        this.childTaskCount = downloadData.getChildTaskCount();
        DownloadData dbData = DownloadDaoImpl.getInstance(context).getDownload(url);
        this.downloadData = dbData == null ? downloadData : dbData;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    public DownloadData getDownloadData() {
        return downloadData;
    }

    public void setFileTask(FileTask fileTask) {
        this.fileTask = fileTask;
    }

    /**
     * 下载中退出时保存数据、释放资源
     */
    public void destroy() {
        if (mCurrentState == Consts.CANCEL || mCurrentState == Consts.PAUSE) {
            return;
        }
        fileTask.destroy();
    }

    /**
     * 暂停（正在下载才可以暂停）
     * 如果文件不支持断点续传则不能进行暂停操作
     */
    public void pause() {
        if (mCurrentState == Consts.PROGRESS) {
            fileTask.pause();
        }
    }

    /**
     * 取消（已经被取消、下载结束则不可取消）
     */
    public void cancel(boolean isNeedRestart) {
        this.isNeedRestart = isNeedRestart;
        if (mCurrentState == Consts.PROGRESS) {
            fileTask.cancel();
        } else if (mCurrentState == Consts.PAUSE || mCurrentState == Consts.ERROR) {
            mHandler.sendEmptyMessage(Consts.CANCEL);
        }
    }
}
