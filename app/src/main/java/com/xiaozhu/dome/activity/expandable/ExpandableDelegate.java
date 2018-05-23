package com.xiaozhu.dome.activity.expandable;

import android.content.Context;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.widget.custom.ExpandableTextView;
import com.xiaozhu.dome.R;

import butterknife.Bind;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 9:33
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ExpandableDelegate extends BaseDelegate {
    @Bind(R.id.expand_text_view)
    ExpandableTextView expandTextView;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_expandable;
    }

    @Override
    public void initWidget(Context context) {
        expandTextView.setText("ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.ExpandableTextView is an Android library that allows developers to easily create an TextView which can expand/collapse just like the Google Play's app description. Feel free to use it all you want in your Android apps provided that you cite this project.");
    }
}
