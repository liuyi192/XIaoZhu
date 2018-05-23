package com.xiaozhu.dome.activity.list;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.base.BaseRecyclerDelegate;
import com.xiaozhu.common.entity.BaseEntity;
import com.xiaozhu.dome.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/13 10:31
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RecyclerDelegate extends BaseRecyclerDelegate {
    @Override
    public void initWidget(Context context) {
        super.initWidget(context);
        autoRecyclerAdapter.setHolder(ListDataViewHolder.class, R.layout.common_list_item);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(BaseApplication.getInstance().getmContext());
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
            }
        }, 3000);
    }
}
