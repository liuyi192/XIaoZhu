package com.xiaozhu.dome.activity;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.eventBus.EventBusEntity;
import com.xiaozhu.common.listeners.CountdownTimerListener;
import com.xiaozhu.common.listeners.TimerListener;
import com.xiaozhu.common.utils.CountdownTimerUtils;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.utils.TimerUtils;
import com.xiaozhu.common.widget.custom.MarqueeView;
import com.xiaozhu.dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/12 11:02
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class MainDelegate extends BaseDelegate {
    private TextView tvTitle;
    private TextView btnBanner;
    private TextView btnFillet;
    private TextView btnProgress;
    private TextView btnLoading;
    private TextView btnIndicator;
    private TextView btnHttp;
    private TextView btnLog;
    private TextView btnWeb;
    private TextView btnTitle;
    private TextView btnExpand;
    private TextView btnRefresh;
    private TextView btnRefreshList;
    private TextView btnDownload;

    private MarqueeView marqueeView;


    private TimerUtils timerUtils;
    private CountdownTimerUtils countdownTimerUtils;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget(Context context) {
        tvTitle = getView(R.id.tvTitle);
        btnBanner = getView(R.id.btnBanner);
        btnFillet = getView(R.id.btnFillet);
        btnProgress = getView(R.id.btnProgress);
        btnLoading = getView(R.id.btnLoading);
        btnIndicator = getView(R.id.btnIndicator);
        btnHttp = getView(R.id.btnHttp);
        btnLog = getView(R.id.btnLog);
        btnWeb = getView(R.id.btnWeb);
        btnTitle = getView(R.id.btnTitle);
        btnExpand = getView(R.id.btnExpand);
        btnRefresh = getView(R.id.btnRefresh);
        btnRefreshList = getView(R.id.btnRefreshList);
        btnDownload = getView(R.id.btnDownload);

        marqueeView = getView(R.id.marqueeView);

        List<CharSequence> list = new ArrayList<>();
        SpannableString ss1 = new SpannableString("1、MarqueeView开源项目");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 2, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、GitHub：sfsheng0322");
        ss2.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、个人博客：sunfusheng.com");
        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、新浪微博：@孙福生微博");
        marqueeView.startWithList(list);
    }

    @Override
    public void mainEvent(EventBusEntity eventBusEntity) {
        super.mainEvent(eventBusEntity);
        switch (eventBusEntity.getType()) {
            case 1001:
                LogUtils.i("=====>>>>点击时间");
                break;
            case 1002:
                timerUtils.clearTimer();
                countdownTimerUtils.closeCountdown();
                break;
        }
    }


    public void initTimer() {
        //计时器
        timerUtils = new TimerUtils();
        timerUtils.setTimerListener(new TimerListener() {
            @Override
            public void onCallback() {
                LogUtils.i("====>>>>>>刷新中...");
            }
        });
        timerUtils.startTimer();
        //倒计时
        countdownTimerUtils = new CountdownTimerUtils(10);
        countdownTimerUtils.setCountdownTimerListener(new CountdownTimerListener() {
            @Override
            public void onTiming(int second) {
                LogUtils.i("倒计时：" + second);
            }

            @Override
            public void onEnd() {
                LogUtils.i("倒计时结束");
            }
        });
        countdownTimerUtils.start();
    }
}
