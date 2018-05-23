package com.xiaozhu.common.widget.banner.hintView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaozhu.common.widget.banner.interfaces.HintView;

/**
 * @说明 形状提示视图
 * @作者 liuyi
 * @时间 2018/4/13 14:58
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class ShapeHintView extends LinearLayout implements HintView {
    private ImageView[] mDots;
    private int length = 0;
    private int lastPosition = 0;
    private Drawable dot_normal;
    private Drawable dot_focus;

    public ShapeHintView(Context context) {
        super(context);
    }

    public ShapeHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract Drawable makeFocusDrawable();

    public abstract Drawable makeNormalDrawable();

    @Override
    public void initView(int length, int gravity) {
        removeAllViews();
        lastPosition = 0;
        setOrientation(HORIZONTAL);
        switch (gravity) {
            case 0:
                setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 1:
                setGravity(Gravity.CENTER);
                break;
            case 2:
                setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
        }
        this.length = length;
        mDots = new ImageView[length];
        dot_focus = makeFocusDrawable();
        dot_normal = makeNormalDrawable();
        for (int i = 0; i < length; i++) {
            mDots[i] = new ImageView(getContext());
            LayoutParams dotlp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            dotlp.setMargins(10, 0, 10, 0);
            mDots[i].setLayoutParams(dotlp);
            mDots[i].setBackgroundDrawable(dot_normal);
            addView(mDots[i]);
        }
        setCurrent(0);
    }

    @Override
    public void setCurrent(int current) {
        if (current < 0 || current > length - 1) {
            return;
        }
        mDots[lastPosition].setBackgroundDrawable(dot_normal);
        mDots[current].setBackgroundDrawable(dot_focus);
        lastPosition = current;
    }
}
