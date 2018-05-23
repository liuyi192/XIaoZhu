package com.xiaozhu.dome.activity.banner;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.widget.banner.LoopPagerAdapter;
import com.xiaozhu.common.widget.banner.RollPagerView;
import com.xiaozhu.dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明 轮播图数据源
 * @作者 liuyi
 * @时间 2018/4/13 15:38
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class BannerAdapter extends LoopPagerAdapter {
    private List<String> listImg = new ArrayList<>();

    public BannerAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    public void addList(List<String> listImg) {
        if (listImg != null && listImg.size() > 0) {
            this.listImg.addAll(listImg);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //图片加载
        Glide.with(BaseApplication.getInstance().getmContext())
                .load(listImg.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
        return view;
    }

    @Override
    public int getRealCount() {
        return listImg.size();
    }
}
