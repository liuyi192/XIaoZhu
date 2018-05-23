package com.xiaozhu.common.widget.banner.hintView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.xiaozhu.common.utils.DisplayUtils;

/**
 * @说明 颜色提示
 * @作者 liuyi
 * @时间 2018/4/13 14:57
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ColorPointHintView extends ShapeHintView {
    private int focusColor;
    private int normalColor;

    public ColorPointHintView(Context context, int focusColor, int normalColor) {
        super(context);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
    }

    @Override
    public Drawable makeFocusDrawable() {
        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(focusColor);
        dot_focus.setCornerRadius(DisplayUtils.dip2px(4));
        dot_focus.setSize(DisplayUtils.dip2px(8), DisplayUtils.dip2px(8));
        return dot_focus;
    }

    @Override
    public Drawable makeNormalDrawable() {
        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setColor(normalColor);
        dot_normal.setCornerRadius(DisplayUtils.dip2px(4));
        dot_normal.setSize(DisplayUtils.dip2px(8), DisplayUtils.dip2px(8));
        return dot_normal;
    }
}
