package com.xiaozhu.common.refresh.constant;

import android.content.Context;

import com.xiaozhu.common.R;
import com.xiaozhu.common.refresh.SmartRefreshLayout;
import com.xiaozhu.common.refresh.footer.BallPulseFooter;
import com.xiaozhu.common.refresh.header.MaterialHeader;

/**
 * @说明 刷新工具类
 * @作者 liuyi
 * @时间 2018/5/4 14:56
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshUtils {
    private SmartRefreshLayout refreshLayout;
    private static RefreshUtils mInstance;

    public static RefreshUtils getInstance() {
        if (mInstance == null) {
            synchronized (RefreshUtils.class) {
                if (mInstance == null) {
                    mInstance = new RefreshUtils();
                }
            }
        }
        return mInstance;
    }


    public void init(Context context, SmartRefreshLayout refreshLayout, RefreshModel refreshModel) {
        this.refreshLayout = refreshLayout;
        //设置刷新控件主题
        refreshLayout.setPrimaryColorsId(R.color.refresh_layout_bg, R.color.refresh_layout_primary);
        refreshLayout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(true));
        //底部控件主题
        BallPulseFooter ballPulseFooter = new BallPulseFooter(context);
        ballPulseFooter.setSpinnerStyle(SpinnerStyle.Scale);
        ballPulseFooter.setBackgroundColor(context.getResources().getColor(R.color.refresh_layout_bg));
        refreshLayout.setRefreshFooter(ballPulseFooter);
        //设置刷新类型
        setRefresh(refreshModel);
    }

    /**
     * 设置刷新
     */
    private void setRefresh(RefreshModel refreshModel) {
        //设置刷新类型
        if (refreshModel == RefreshModel.PULL_FROM_START) {//头部刷新
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setEnableLoadMore(false);
        } else if (refreshModel == RefreshModel.PULL_FROM_END) {//底部加载
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(true);
        } else if (refreshModel == RefreshModel.BOTH) {//刷新和加载
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setEnableLoadMore(true);
        } else {//不刷新也不加载
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(false);
        }
    }
}
