package com.xiaozhu.common.widget.fillet;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Checkable;

import com.xiaozhu.common.widget.fillet.helper.FilletAttrs;
import com.xiaozhu.common.widget.fillet.helper.FilletHelper;

/**
 * @说明 圆角图片
 * @作者 liuyi
 * @时间 2018/4/18 10:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class FilletImageView extends AppCompatImageView implements Checkable, FilletAttrs {
    private FilletHelper mFilletHelper;

    public FilletImageView(Context context) {
        this(context, null);
    }

    public FilletImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilletImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFilletHelper = new FilletHelper();
        mFilletHelper.initAttrs(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mFilletHelper.onSizeChanged(this, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        mFilletHelper.refreshRegion(this);
        if (mFilletHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(mFilletHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(mFilletHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mFilletHelper.onClipDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
            refreshDrawableState();
        } else if (action == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
            refreshDrawableState();
        }
        if (!mFilletHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
            setPressed(false);
            refreshDrawableState();
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    //--- 公开接口 ----
    public void setClipBackground(boolean clipBackground) {
        mFilletHelper.mClipBackground = clipBackground;
        invalidate();
    }

    public void setRoundAsCircle(boolean roundAsCircle) {
        mFilletHelper.mRoundAsCircle = roundAsCircle;
        invalidate();
    }

    public void setRadius(int radius) {
        for (int i = 0; i < mFilletHelper.radii.length; i++) {
            mFilletHelper.radii[i] = radius;
        }
        invalidate();
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mFilletHelper.radii[0] = topLeftRadius;
        mFilletHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(int topRightRadius) {
        mFilletHelper.radii[2] = topRightRadius;
        mFilletHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mFilletHelper.radii[4] = bottomLeftRadius;
        mFilletHelper.radii[5] = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mFilletHelper.radii[6] = bottomRightRadius;
        mFilletHelper.radii[7] = bottomRightRadius;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        mFilletHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        mFilletHelper.mStrokeColor = strokeColor;
        invalidate();
    }

    public boolean isClipBackground() {
        return mFilletHelper.mClipBackground;
    }

    public boolean isRoundAsCircle() {
        return mFilletHelper.mRoundAsCircle;
    }

    public float getTopLeftRadius() {
        return mFilletHelper.radii[0];
    }

    public float getTopRightRadius() {
        return mFilletHelper.radii[2];
    }

    public float getBottomLeftRadius() {
        return mFilletHelper.radii[4];
    }

    public float getBottomRightRadius() {
        return mFilletHelper.radii[6];
    }

    public int getStrokeWidth() {
        return mFilletHelper.mStrokeWidth;
    }

    public int getStrokeColor() {
        return mFilletHelper.mStrokeColor;
    }


    //--- Selector 支持 ----

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mFilletHelper.drawableStateChanged(this);
    }

    @Override
    public void setChecked(boolean checked) {
        if (mFilletHelper.mChecked != checked) {
            mFilletHelper.mChecked = checked;
            refreshDrawableState();
            if (mFilletHelper.mOnCheckedChangeListener != null) {
                mFilletHelper.mOnCheckedChangeListener.onCheckedChanged(this, mFilletHelper.mChecked);
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mFilletHelper.mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mFilletHelper.mChecked);
    }

    public void setOnCheckedChangeListener(FilletHelper.OnCheckedChangeListener listener) {
        mFilletHelper.mOnCheckedChangeListener = listener;
    }
}
