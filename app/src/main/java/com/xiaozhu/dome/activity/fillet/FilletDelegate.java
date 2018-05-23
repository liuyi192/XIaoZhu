package com.xiaozhu.dome.activity.fillet;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.widget.fillet.FilletImageView;
import com.xiaozhu.dome.R;

/**
 * @说明 圆角控件
 * @作者 liuyi
 * @时间 2018/4/18 11:07
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class FilletDelegate extends BaseDelegate {
    private ImageView images;
    private FilletImageView filletImg;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_fillet;
    }

    @Override
    public void initWidget(Context context) {
        images = getView(R.id.images);
        filletImg = getView(R.id.filletImg);

        //图片加载
        Glide.with(BaseApplication.getInstance().getmContext())
                .load("http://imgsrc.baidu.com/imgad/pic/item/34fae6cd7b899e51fab3e9c048a7d933c8950d21.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .into(images);


        Glide.with(BaseApplication.getInstance().getmContext())
                .load("http://imgsrc.baidu.com/imgad/pic/item/34fae6cd7b899e51fab3e9c048a7d933c8950d21.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .into(filletImg);

    }
}
