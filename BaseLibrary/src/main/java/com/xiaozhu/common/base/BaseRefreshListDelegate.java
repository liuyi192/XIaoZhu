package com.xiaozhu.common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaozhu.common.R;
import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.refresh.SmartRefreshLayout;
import com.xiaozhu.common.refresh.api.RefreshLayout;
import com.xiaozhu.common.refresh.constant.RefreshModel;
import com.xiaozhu.common.refresh.constant.RefreshState;
import com.xiaozhu.common.refresh.constant.RefreshUtils;
import com.xiaozhu.common.refresh.listener.OnRefreshLoadMoreListener;
import com.xiaozhu.common.refresh.listener.SimpleMultiPurposeListener;
import com.xiaozhu.common.widget.autoAdapter.AutoRecyclerAdapter;
import com.xiaozhu.common.widget.loading.LoadDataLayout;

/**
 * @说明 刷新
 * @作者 liuyi
 * @时间 2018/5/4 14:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseRefreshListDelegate extends BaseDelegate {
    protected RecyclerView recyclerView;
    protected SmartRefreshLayout refreshLayout;
    protected LoadDataLayout loadView;
    protected AutoRecyclerAdapter autoRecyclerAdapter;
    protected int defaultPage = 0;

    @Override
    public int getRootLayoutId() {
        return R.layout.common_base_refresh_list;
    }

    @Override
    public void initWidget(Context context) {
        recyclerView = getView(R.id.recyclerView);
        refreshLayout = getView(R.id.refreshLayout);
        loadView = getView(R.id.loadView);
        //设置列表数据源
        autoRecyclerAdapter = new AutoRecyclerAdapter();
        recyclerView.setLayoutManager(getLayoutManager() == null ? new LinearLayoutManager(BaseApplication.getInstance().getmContext()) : getLayoutManager());
        recyclerView.setAdapter(autoRecyclerAdapter);
        defaultPage = context.getResources().getInteger(R.integer.default_page);
        //设置样式
        RefreshUtils.getInstance().init(context, refreshLayout, getRefreshModel());
        //设置刷新监听
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                loadingData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                startRefresh();
            }
        });
        //刷新状态监听
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                super.onStateChanged(refreshLayout, oldState, newState);
                if (oldState == RefreshState.LoadFinish && newState == RefreshState.None) { //加载完成
                    stopRefresh();
                } else if (oldState == RefreshState.RefreshFinish && newState == RefreshState.None) {//刷新完成
                    stopRefresh();
                }
            }
        });
        refreshLayout.autoRefresh();
    }

    public void startRefresh() {
        if (autoRecyclerAdapter.getItemCount() > 0) {
            autoRecyclerAdapter.clear();
            autoRecyclerAdapter.clearHolder();
        }
        defaultPage = getActivity().getResources().getInteger(R.integer.default_page);
        loadingData();
    }

    public void stopRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (autoRecyclerAdapter.getItemCount() > 0) {
            loadView.setStatus(LoadDataLayout.SUCCESS);
        } else {
            loadView.setStatus(LoadDataLayout.EMPTY);
        }
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract void loadingData();

    public abstract RefreshModel getRefreshModel();

}
