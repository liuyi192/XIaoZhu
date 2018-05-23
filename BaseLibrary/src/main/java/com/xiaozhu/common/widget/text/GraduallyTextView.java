package com.xiaozhu.common.widget.text;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.xiaozhu.common.utils.DisplayUtils;

/**
 * @说明 渐渐地显示控件
 * @作者 liuyi
 * @时间 2018/4/22 下午2:35
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class GraduallyTextView extends AppCompatEditText {
    private CharSequence text;//自定义的文本
    private int startY = 0;
    private float progress;//读取进度条
    private boolean isLoading;//判断是否正在读取
    private Paint mPaint;
    private int textLength;//设置文本长度
    private boolean isStop = true;
    private int duration = 2000;//总时长
    private float sigleDuration;//单字时长

    private ValueAnimator valueAnimator;//设置属性平移、缩放动画

    public GraduallyTextView(Context context) {
        super(context);
        init();
    }

    public GraduallyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraduallyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        setBackground(null);
        setCursorVisible(false);
        setFocusable(false);
        setEnabled(false);
        setFocusableInTouchMode(false);
        //设置平移动画
        valueAnimator = ValueAnimator.ofFloat(0, 100).setDuration(duration);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //设置监听，当动画更新的时候触发重绘
                progress = (Float) animation.getAnimatedValue();
                GraduallyTextView.this.invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaint.setTextSize(getTextSize());
        float width = mPaint.measureText(text.toString());
        float fontSpacing = mPaint.getFontSpacing();
        setMeasuredDimension((int) width, (int) fontSpacing);
    }

    //设置开始读取的方法
    public void startLoading() {
        init();
        if (!isStop) {
            return;
        }
        textLength = getText().length();
        if (TextUtils.isEmpty(getText().toString())) {
            return;
        }
        isLoading = true;
        isStop = false;
        text = getText();

        mPaint.setTextSize(getTextSize());
        mPaint.setColor(getCurrentTextColor());
        int height = (int) mPaint.getFontSpacing();
        //计算startY的值,应该是dialog中心点需要下移的值
        //计算方法startY = (progressbarHeight +marginTop+loadviewHeight)/2
        startY = (int) (height + DisplayUtils.dip2px(60)) / 2;
        setMinWidth(getWidth());
        setText("");//清空
        setHint("");//清空
        valueAnimator.start();
        //计算单个字的progress
        sigleDuration = 100f / textLength;
    }

    public boolean isLoading() {
        return isLoading;
    }

    //停止loading的方法
    public void stopLoading() {
        isLoading = false;
        isStop = true;
        if (valueAnimator != null) {
            valueAnimator.end();
            valueAnimator.cancel();
            setText(text);
        }
    }

    //设置时长
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (!isLoading) {
            return;
        }
        if (visibility == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                valueAnimator.resume();
            } else {
                startLoading();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                valueAnimator.pause();
            } else {
                stopLoading();
            }
        }
    }

    //重写ondraw方法，如果还在loading，而且进度还小于1,则让它和透明度联动
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isStop) {
            mPaint.setAlpha(255);
            //当progress进度大于一个字的进度时
            if (progress / sigleDuration >= 1) {
                canvas.drawText(String.valueOf(text), 0, (int) (progress / sigleDuration), 0, startY, mPaint);
            }
            //设置即将出现那个字的透明度
            mPaint.setAlpha((int) (255 * ((progress % sigleDuration) / sigleDuration)));
            //下一个显示出来的字的position
            int nextOne = (int) (progress / sigleDuration);
            //如果position小于textLength
            if (nextOne < textLength) {
                //即将显示那一个字的透明度
                //measureText测量出text的宽度
                canvas.drawText(String.valueOf(text.charAt(nextOne)), 0, 1, getPaint().measureText(text.subSequence(0, nextOne).toString()), startY, mPaint);
            }
        }
    }
}
