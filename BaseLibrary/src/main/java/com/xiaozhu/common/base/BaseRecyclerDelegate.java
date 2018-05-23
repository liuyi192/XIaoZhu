package com.xiaozhu.common.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaozhu.common.R;
import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.widget.autoAdapter.AutoRecyclerAdapter;
import com.xiaozhu.common.widget.loading.LoadDataLayout;

/**
 * @说明 回收列表基类委托
 * @作者 liuyi
 * @时间 2018/4/13 10:13
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseRecyclerDelegate extends BaseDelegate {
    protected LoadDataLayout loadView;
    protected RecyclerView recyclerView;
    protected AutoRecyclerAdapter autoRecyclerAdapter;

    @Override
    public int getRootLayoutId() {
        return R.layout.common_base_list;
    }

    @Override
    public void initWidget(Context context) {
        recyclerView = getView(R.id.recyclerView);
        loadView = getView(R.id.loadView);
        recyclerView.setLayoutManager(getLayoutManager() == null ? new LinearLayoutManager(BaseApplication.getInstance().getmContext()) : getLayoutManager());
        autoRecyclerAdapter = new AutoRecyclerAdapter();
        recyclerView.setAdapter(autoRecyclerAdapter);
        loadView.setOnReloadListener(new LoadDataLayout.OnReloadListener() {
            @Override
            public void onReload(View v, int status) {
                switch (status) {
                    case LoadDataLayout.EMPTY:
                    case LoadDataLayout.ERROR:
                        loadView.setStatus(LoadDataLayout.LOADING);
                        loadingData();
                        break;
                }
            }
        });
    }

    @Override
    public void business(Context context) {
        loadView.setStatus(LoadDataLayout.LOADING);
        loadingData();
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract void loadingData();

    /**
     * 结束加载
     */
    public void stopLoad() {
        if (autoRecyclerAdapter.getItemCount() > 0) {
            loadView.setStatus(LoadDataLayout.SUCCESS);
        } else {
            loadView.setStatus(LoadDataLayout.EMPTY);
        }
    }
}
