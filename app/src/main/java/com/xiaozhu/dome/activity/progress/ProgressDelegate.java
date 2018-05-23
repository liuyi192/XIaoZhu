package com.xiaozhu.dome.activity.progress;

import android.content.Context;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.listeners.TimerListener;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.utils.TimerUtils;
import com.xiaozhu.common.widget.progress.NumberProgressBar;
import com.xiaozhu.common.widget.progress.OnProgressBarListener;
import com.xiaozhu.common.widget.progress.RoundProgressBar;
import com.xiaozhu.dome.R;

/**
 * @说明 进度条
 * @作者 liuyi
 * @时间 2018/4/18 14:12
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ProgressDelegate extends BaseDelegate {
    private NumberProgressBar progressBar;
    private RoundProgressBar circleProgress;
    private RoundProgressBar horizontalProgress;
    private RoundProgressBar sectorProgress;
    private int progress = 0;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_progress;
    }

    @Override
    public void initWidget(Context context) {
        progressBar = getView(R.id.progressBar);
        circleProgress = getView(R.id.circleProgress);
        horizontalProgress = getView(R.id.horizontalProgress);
        sectorProgress = getView(R.id.sectorProgress);
        circleProgress.setMax(100);
        horizontalProgress.setMax(100);
        sectorProgress.setMax(100);

        progressBar.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                LogUtils.i("=====>>>   current:" + current + "     max:" + max);
            }
        });
        reshProgress();
    }

    private void reshProgress() {
        final TimerUtils timerUtils = new TimerUtils();
        timerUtils.setTimerListener(new TimerListener() {
            @Override
            public void onCallback() {
                progress++;
                if (progress <= 100) {
                    progressBar.incrementProgressBy(1);
                    circleProgress.setProgress(progress);
                    horizontalProgress.setProgress(progress);
                    sectorProgress.setProgress(progress);
                } else {
                    timerUtils.clearTimer();
                }
            }
        });
        timerUtils.startTimer();
    }
}
