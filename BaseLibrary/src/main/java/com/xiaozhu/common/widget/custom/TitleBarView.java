package com.xiaozhu.common.widget.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaozhu.common.R;

/**
 * @说明 标题栏
 * @作者 liuyi
 * @时间 2018/5/3 9:16
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class TitleBarView extends FrameLayout {
    private ImageView btnIvLeft;
    private TextView btnLeft;
    private TextView tvTitle;
    private ImageView btnIvRight;
    private TextView btnTvRight;
    private RelativeLayout titleBar;
    private View contentView;

    public TitleBarView(@NonNull Context context) {
        this(context, null);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        contentView = LayoutInflater.from(context).inflate(R.layout.common_title_bar, null);
        btnIvLeft = contentView.findViewById(R.id.btnIvLeft);
        btnLeft = contentView.findViewById(R.id.btnLeft);
        tvTitle = contentView.findViewById(R.id.tvTitle);
        btnIvRight = contentView.findViewById(R.id.btnIvRight);
        btnTvRight = contentView.findViewById(R.id.btnTvRight);
        titleBar = contentView.findViewById(R.id.titleBar);
        addView(contentView);
    }

    public void setTitle(int resId) {
        setTitle(getResources().getString(resId));
    }

    public void setTitle(String name) {
        ((TextView) getView(R.id.tvTitle)).setText(name);
    }

    public void setBtnTvLeft(int resId) {
        setBtnTvLeft(getResources().getString(resId));
    }

    public void setBtnTvLeft(String name) {
        setViewVisible(R.id.btnLeft);
        ((TextView) getView(R.id.tvTitle)).setText(name);
    }

    public void setBtnIvLeft(int resId) {
        setViewVisible(R.id.btnIvLeft);
        ((ImageView) getView(R.id.btnIvLeft)).setImageResource(resId);
    }

    public void setBtnIvRight(int resId) {
        setViewVisible(R.id.btnIvRight);
        ((ImageView) getView(R.id.btnIvRight)).setImageResource(resId);
    }

    public void setBtnTvRight(int resId) {
        setBtnTvRight(getResources().getString(resId));
    }

    public void setBtnTvRight(String name) {
        setViewVisible(R.id.btnTvRight);
        ((TextView) getView(R.id.btnTvRight)).setText(name);
    }

    public void setTitleBar(RelativeLayout titleBar) {
        this.titleBar = titleBar;
    }

    public void setViewVisible(int... ids) {
        for (int id : ids) {
            getView(id).setVisibility(VISIBLE);
        }
    }

    public void setViewGone(int... ids) {
        for (int id : ids) {
            getView(id).setVisibility(GONE);
        }
    }

    public <T extends View> T getView(int id) {
        if (contentView != null) {
            return contentView.findViewById(id);
        }
        return null;
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }
}
