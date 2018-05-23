package com.xiaozhu.dome.activity.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaozhu.common.base.activitys.BaseActivity;
import com.xiaozhu.common.widget.indicator.IndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/23 15:02
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class IndicatorActivity extends BaseActivity<IndicatorDelegate> {
    private List<String> mList = Arrays.asList("军事", "政治", "娱乐", "头条", "段子", "视频", "直播");
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    @Override
    public Class getDelegateClass() {
        return IndicatorDelegate.class;
    }

    @Override
    public void bindEvenListener() {

    }

    @Override
    public void business() {
        for (String data : mList) {
            IndicatorFragment fragment = IndicatorFragment.newInstance(data);
            mTabContents.add(fragment);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };

        // 设置Tab上的标题
        ((IndicatorDelegate) viewDelegate).indicator.setTitleList(mList);
        // 设置关联的ViewPager
        ((IndicatorDelegate) viewDelegate).indicator.setViewPager(((IndicatorDelegate) viewDelegate).viewPager, 0);
        //设置Adapter
        ((IndicatorDelegate) viewDelegate).viewPager.setAdapter(mAdapter);

        ((IndicatorDelegate) viewDelegate).indicator.setOnIndicatorSelected(new IndicatorView.OnIndicatorSelected() {
            @Override
            public void setOnIndicatorSelected(int position, String title) {

            }
        });
        // PageChange监听
        ((IndicatorDelegate) viewDelegate).indicator.setOnPageChangeListener(new IndicatorView.PageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
