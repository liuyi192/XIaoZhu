package com.xiaozhu.common.refresh.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

/**
 * @说明 圆形图片
 * @作者 liuyi
 * @时间 2018/5/4 14:09
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CircleImageView extends AppCompatImageView {
    protected static final int KEY_SHADOW_COLOR = 0x1E000000;
    protected static final int FILL_SHADOW_COLOR = 0x3D000000;
    protected static final float X_OFFSET = 0f;
    protected static final float Y_OFFSET = 1.75f;
    protected static final float SHADOW_RADIUS = 3.5f;
    protected static final int SHADOW_ELEVATION = 4;
    int mShadowRadius;

    public CircleImageView(Context context, int color) {
        super(context);
        final View thisView = this;
        final float density = thisView.getResources().getDisplayMetrics().density;
        final int shadowYOffset = (int) (density * Y_OFFSET);
        final int shadowXOffset = (int) (density * X_OFFSET);
        mShadowRadius = (int) (density * SHADOW_RADIUS);
        ShapeDrawable circle;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            circle = new ShapeDrawable(new OvalShape());
            thisView.setElevation(SHADOW_ELEVATION * density);
        } else {
            OvalShape oval = new OvalShadow(mShadowRadius);
            circle = new ShapeDrawable(oval);
            thisView.setLayerType(LAYER_TYPE_SOFTWARE, circle.getPaint());
            circle.getPaint().setShadowLayer(mShadowRadius, shadowXOffset, shadowYOffset, KEY_SHADOW_COLOR);
            final int padding = mShadowRadius;
            thisView.setPadding(padding, padding, padding, padding);
        }
        circle.getPaint().setColor(color);
        if (Build.VERSION.SDK_INT >= 16) {
            thisView.setBackground(circle);
        } else {
            thisView.setBackgroundDrawable(circle);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View thisView = this;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (Build.VERSION.SDK_INT < 21) {
            super.setMeasuredDimension(thisView.getMeasuredWidth() + mShadowRadius * 2, thisView.getMeasuredHeight() + mShadowRadius * 2);
        }
    }

    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;

        OvalShadow(int shadowRadius) {
            super();
            mShadowPaint = new Paint();
            mShadowRadius = shadowRadius;
            updateRadialGradient((int) super.rect().width());
        }

        @Override
        protected void onResize(float width, float height) {
            super.onResize(width, height);
            updateRadialGradient((int) width);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            final View thisView = CircleImageView.this;
            final int viewWidth = thisView.getWidth();
            final int viewHeight = thisView.getHeight();
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth / 2, mShadowPaint);
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth / 2 - mShadowRadius, paint);
        }

        private void updateRadialGradient(int diameter) {
            mRadialGradient = new RadialGradient(diameter / 2, diameter / 2, mShadowRadius, new int[]{FILL_SHADOW_COLOR, Color.TRANSPARENT}, null, Shader.TileMode.CLAMP);
            mShadowPaint.setShader(mRadialGradient);
        }
    }
}
