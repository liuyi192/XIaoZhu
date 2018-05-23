package com.xiaozhu.dome.activity.banner;

import android.content.Context;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.widget.banner.OnItemClickListener;
import com.xiaozhu.common.widget.banner.RollPagerView;
import com.xiaozhu.common.widget.banner.hintView.ColorPointHintView;
import com.xiaozhu.dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/13 15:16
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class BannerDelegate extends BaseDelegate {
    private RollPagerView bannerView;
    private BannerAdapter bannerAdapter;

    private Context mContext;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    public void initWidget(Context context) {
        mContext = BaseApplication.getInstance().getmContext();
        bannerView = getView(R.id.bannerView);
        //设置滑动时间
        bannerView.setPlayDelay(3000);
        //设置数据源
        bannerAdapter = new BannerAdapter(bannerView);
        bannerView.setAdapter(bannerAdapter);
        //设置提示控件
        bannerView.setHintView(new ColorPointHintView(mContext, mContext.getResources().getColor(R.color.banner_focus), mContext.getResources().getColor(R.color.banner_normal)));
        //设置点击事件
        bannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtils.i("====>>>>  " + position);
            }
        });
        //
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703339&di=6bdaf2e52910df9a02bcc39c634b1e07&imgtype=0&src=http%3A%2F%2Fpic32.photophoto.cn%2F20140718%2F0033034334383169_b.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703338&di=b2a13393215ba35fb4128b666dc97ee0&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe1fe9925bc315c602050233b87b1cb1348547718.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703338&di=56f4a229203ab24d3c3be38f502b3655&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D9c77920db312c8fca0fefe8e946af830%2Fc2cec3fdfc039245387401228d94a4c27c1e25de.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703336&di=b651cd0d37c57deaa72c79e383014dc8&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D356c0ad0c9fdfc03f175ebfbbc56ede1%2F9345d688d43f87945952b400d81b0ef41bd53a6b.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703335&di=b51f2eff6f511e4fd3a85519e88d4966&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D8cc7e60ab91bb0519b29bb6b5e13b0c1%2Ff9198618367adab46f5df16b81d4b31c8701e40d.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703335&di=c989842c32cf51c77f194c2fa5bc0650&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D28e7b0cfa76eddc432eabcb851b2dc88%2F50da81cb39dbb6fd74757b0c0324ab18972b37e9.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703335&di=95f705d2abdedefb8a3bf214086ee218&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dfa00bd86a3014c080d3620e66212687d%2F6f061d950a7b020868d00fce68d9f2d3572cc887.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703334&di=b99063bcf16d802fe059128f8c786447&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D0ca30bfa7cc6a7efad2ba0659593c524%2Fcf1b9d16fdfaaf51186c2f9a865494eef01f7a0c.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703331&di=701f1d52031c9e545b5a8379935dc89c&imgtype=0&src=http%3A%2F%2Fpic1.16pic.com%2F00%2F26%2F30%2F16pic_2630859_b.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703331&di=7c757efc392038d96ab89e37834aec04&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151110%2F14-1511101633533T.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523615703330&di=77e218df707b82b16ec6e3dd585e710f&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151113%2F14-15111310522aG.jpg");
        bannerAdapter.addList(list);
    }


}
