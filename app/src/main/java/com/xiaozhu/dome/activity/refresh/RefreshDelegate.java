package com.xiaozhu.dome.activity.refresh;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaozhu.common.base.BaseRecyclerDelegate;
import com.xiaozhu.common.entity.BaseEntity;
import com.xiaozhu.common.refresh.SmartRefreshLayout;
import com.xiaozhu.common.refresh.api.RefreshLayout;
import com.xiaozhu.common.refresh.listener.OnRefreshListener;
import com.xiaozhu.common.widget.loading.LoadDataLayout;
import com.xiaozhu.dome.R;
import com.xiaozhu.dome.activity.list.ListDataViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 11:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshDelegate extends BaseRecyclerDelegate {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.loadView)
    LoadDataLayout loadView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void initWidget(Context context) {
        super.initWidget(context);
        autoRecyclerAdapter.setHolder(ListDataViewHolder.class, R.layout.common_list_item);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void loadingData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<BaseEntity> list = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    list.add(new BaseEntity());
                }
                autoRecyclerAdapter.setDataList(ListDataViewHolder.class, list);
                stopLoad();
                refreshLayout.finishRefresh();
            }
        }, 3000);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_slide;
    }
}
