package com.xiaozhu.common.widget.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaozhu.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明 加载动画
 * @作者 liuyi
 * @时间 2018/4/13 16:19
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class LoadingAnimationView extends View {
    private static final float MAX = 30;
    private static final int STEP = 1;
    private static final int RADIUS = 80;
    private static final float MOVING_RADIUS = 10f;
    private static final float INCREASE = MOVING_RADIUS * 0.4f;
    private double x;
    private double y;
    private float radius;
    private static final int DEFAULT_NUMBER = 3;
    private int pointNumber = DEFAULT_NUMBER;

    private int paintColor[] = {getResources().getColor(R.color.loading_color1), getResources().getColor(R.color.loading_color2), getResources().getColor(R.color.loading_color3)};
    private float progress[] = {0, MAX / 3, MAX / 3 * 2};
    private List<Paint> paints;

    public LoadingAnimationView(Context context) {
        this(context, null);
    }

    public LoadingAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paints = new ArrayList<>();
        for (int i = 0; i < pointNumber; i++) {
            Paint paint = new Paint();
            paint.setColor(paintColor[i]);
            paints.add(paint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < pointNumber; i++) {
            progress[i] = progress[i] + STEP;
            radius = getRadiusByProgress(progress[i]);
            canvas.drawCircle((float) (getWidth() / 2 + x), getHeight() / 2, radius + 10, paints.get(i));
        }
        postInvalidateDelayed(40);
    }

    /**
     * 获取进度半径
     *
     * @param progress
     * @return
     */
    private float getRadiusByProgress(float progress) {
        x = RADIUS * Math.cos(Math.toRadians(((double) progress % MAX) / MAX * 360));
        y = RADIUS * Math.sin(Math.toRadians(((double) progress % MAX) / MAX * 360));
        if (y < 0) {
            return (float) (MOVING_RADIUS + (1 - Math.abs(x) / RADIUS) * INCREASE);
        } else {
            return (float) (MOVING_RADIUS - (1 - Math.abs(x) / RADIUS) * INCREASE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    /**
     * 测量
     *
     * @param measureSpec
     * @param isWidth
     * @return
     */
    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }
}
