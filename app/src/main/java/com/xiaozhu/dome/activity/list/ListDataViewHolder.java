package com.xiaozhu.dome.activity.list;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.common.entity.BaseEntity;
import com.xiaozhu.common.widget.autoAdapter.AutoHolder;
import com.xiaozhu.common.widget.custom.TextDrawable;
import com.xiaozhu.dome.R;

import java.util.Map;

import butterknife.Bind;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/13 10:38
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ListDataViewHolder extends AutoHolder<BaseEntity> {
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    public ListDataViewHolder(View itemView, Map<String, Object> dataMap) {
        super(itemView, dataMap);
    }

    @Override
    public void bind(int position, BaseEntity bean) {
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .fontSize(50)
                .textColor(Color.WHITE)
                .endConfig()
                .buildRound("等待", Color.YELLOW);
        icon.setImageDrawable(drawable);
        tvTitle.setText("等待测试  " + position);
    }
}
