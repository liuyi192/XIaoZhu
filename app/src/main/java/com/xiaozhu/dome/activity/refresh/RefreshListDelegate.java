package com.xiaozhu.dome.activity.refresh;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaozhu.common.base.BaseRefreshListDelegate;
import com.xiaozhu.common.entity.BaseEntity;
import com.xiaozhu.common.refresh.constant.RefreshModel;
import com.xiaozhu.dome.R;
import com.xiaozhu.dome.activity.list.ListDataViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 15:26
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RefreshListDelegate extends BaseRefreshListDelegate {
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void initWidget(Context context) {
        super.initWidget(context);
        autoRecyclerAdapter.setHolder(ListDataViewHolder.class, R.layout.common_list_item);
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
                stopRefresh();
            }
        }, 3000);
    }

    @Override
    public RefreshModel getRefreshModel() {
        return RefreshModel.PULL_FROM_START;
    }
}
