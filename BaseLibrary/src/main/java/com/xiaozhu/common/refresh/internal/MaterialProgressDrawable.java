package com.xiaozhu.common.refresh.internal;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 14:04
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float FULL_ROTATION = 1080.0f;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LARGE, DEFAULT})
    public @interface ProgressDrawableSize {
    }

    public static final byte LARGE = 0;
    public static final byte DEFAULT = 1;
    private static final byte CIRCLE_DIAMETER = 40;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final byte CIRCLE_DIAMETER_LARGE = 56;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final float STROKE_WIDTH_LARGE = 3f;

    private static final int[] COLORS = new int[]{
            Color.BLACK
    };
    private static final float COLOR_START_DELAY_OFFSET = 0.75f;
    private static final float END_TRIM_START_DELAY_OFFSET = 0.5f;
    private static final float START_TRIM_DURATION_OFFSET = 0.5f;
    private static final int ANIMATION_DURATION = 1332;
    private static final byte NUM_POINTS = 5;
    private final List<Animation> mAnimators = new ArrayList<>();
    private final Ring mRing = new Ring();
    private float mRotation;
    private static final byte ARROW_WIDTH = 10;
    private static final byte ARROW_HEIGHT = 5;
    private static final float ARROW_OFFSET_ANGLE = 5;
    private static final byte ARROW_WIDTH_LARGE = 12;
    private static final byte ARROW_HEIGHT_LARGE = 6;
    private static final float MAX_PROGRESS_ARC = .8f;

    private View mParent;
    private Animation mAnimation;
    float mRotationCount;
    private float mWidth;
    private float mHeight;
    boolean mFinishing;

    public MaterialProgressDrawable(View parent) {
        mParent = parent;
        setColorSchemeColors(COLORS);
        updateSizes(DEFAULT);
        setupAnimators();
    }

    private void setSizeParameters(int progressCircleWidth, int progressCircleHeight, float centerRadius, float strokeWidth, float arrowWidth, float arrowHeight) {
        final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        final float screenDensity = metrics.density;
        mWidth = progressCircleWidth * screenDensity;
        mHeight = progressCircleHeight * screenDensity;
        mRing.setColorIndex(0);
        mRing.mPaint.setStrokeWidth(strokeWidth * screenDensity);
        mRing.mStrokeWidth = strokeWidth * screenDensity;
        mRing.mRingCenterRadius = (centerRadius * screenDensity);
        mRing.mArrowWidth = (int) (arrowWidth * screenDensity);
        mRing.mArrowHeight = (int) (arrowHeight * screenDensity);
        mRing.setInsets((int) mWidth, (int) mHeight);
        final Drawable thisDrawable = this;
        thisDrawable.invalidateSelf();
    }

    public void updateSizes(@ProgressDrawableSize int size) {
        if (size == LARGE) {
            setSizeParameters(CIRCLE_DIAMETER_LARGE, CIRCLE_DIAMETER_LARGE, CENTER_RADIUS_LARGE, STROKE_WIDTH_LARGE, ARROW_WIDTH_LARGE, ARROW_HEIGHT_LARGE);
        } else {
            setSizeParameters(CIRCLE_DIAMETER, CIRCLE_DIAMETER, CENTER_RADIUS, STROKE_WIDTH, ARROW_WIDTH, ARROW_HEIGHT);
        }
    }

    public void showArrow(boolean show) {
        if (mRing.mShowArrow != show) {
            mRing.mShowArrow = show;
            final Drawable thisDrawable = this;
            thisDrawable.invalidateSelf();
        }
    }

    public void setArrowScale(float scale) {
        if (mRing.mArrowScale != scale) {
            mRing.mArrowScale = scale;
            final Drawable thisDrawable = this;
            thisDrawable.invalidateSelf();
        }
    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        mRing.mStartTrim = (startAngle);
        mRing.mEndTrim = (endAngle);
        final Drawable thisDrawable = this;
        thisDrawable.invalidateSelf();
    }

    public void setProgressRotation(float rotation) {
        mRing.mRotation = (rotation);
        final Drawable thisDrawable = this;
        thisDrawable.invalidateSelf();
    }

    public void setBackgroundColor(@ColorInt int color) {
        mRing.mBackgroundColor = (color);
    }

    public void setColorSchemeColors(int... colors) {
        mRing.mColors = (colors);
        mRing.setColorIndex(0);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mHeight;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mWidth;
    }

    @Override
    public void draw(@NonNull Canvas c) {
        final Drawable thisDrawable = this;
        final Rect bounds = thisDrawable.getBounds();
        final int saveCount = c.save();
        c.rotate(mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        mRing.draw(c, bounds);
        c.restoreToCount(saveCount);
    }

    @Override
    public void setAlpha(int alpha) {
        mRing.mAlpha = (alpha);
    }

    public int getAlpha() {
        return mRing.mAlpha;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mRing.mPaint.setColorFilter(colorFilter);
        final Drawable thisDrawable = this;
        thisDrawable.invalidateSelf();
    }

    void setRotation(float rotation) {
        mRotation = rotation;
        final Drawable thisDrawable = this;
        thisDrawable.invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public boolean isRunning() {
        final List<Animation> animators = mAnimators;
        final int N = animators.size();
        for (int i = 0; i < N; i++) {
            final Animation animator = animators.get(i);
            if (animator.hasStarted() && !animator.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        mAnimation.reset();
        mRing.storeOriginals();
        if (mRing.mEndTrim != mRing.mStartTrim) {
            mFinishing = true;
            mAnimation.setDuration(ANIMATION_DURATION / 2);
            mParent.startAnimation(mAnimation);
        } else {
            mRing.setColorIndex(0);
            mRing.resetOriginals();
            mAnimation.setDuration(ANIMATION_DURATION);
            mParent.startAnimation(mAnimation);
        }
    }

    @Override
    public void stop() {
        mParent.clearAnimation();
        mRing.setColorIndex(0);
        mRing.resetOriginals();
        showArrow(false);
        setRotation(0);
    }

    float getMinProgressArc(Ring ring) {
        return (float) Math.toRadians(ring.mStrokeWidth / (2 * Math.PI * ring.mRingCenterRadius));
    }

    @SuppressWarnings("RedundantCast")
    private int evaluateColorChange(float fraction, int startValue, int endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) | (int) ((startR + (int) (fraction * (endR - startR))) << 16) | (int) ((startG + (int) (fraction * (endG - startG))) << 8) | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    void updateRingColor(float interpolatedTime, Ring ring) {
        if (interpolatedTime > COLOR_START_DELAY_OFFSET) {
            ring.mCurrentColor = (evaluateColorChange((interpolatedTime - COLOR_START_DELAY_OFFSET) / (1.0f - COLOR_START_DELAY_OFFSET), ring.getStartingColor(), ring.getNextColor()));
        }
    }

    void applyFinishTranslation(float interpolatedTime, Ring ring) {
        updateRingColor(interpolatedTime, ring);
        float targetRotation = (float) (Math.floor(ring.mStartingRotation / MAX_PROGRESS_ARC) + 1f);
        final float minProgressArc = getMinProgressArc(ring);
        final float startTrim = ring.mStartingStartTrim + (ring.mStartingEndTrim - minProgressArc - ring.mStartingStartTrim) * interpolatedTime;
        setStartEndTrim(startTrim, ring.mStartingEndTrim);
        final float rotation = ring.mStartingRotation + ((targetRotation - ring.mStartingRotation) * interpolatedTime);
        setProgressRotation(rotation);
    }

    private void setupAnimators() {
        final Ring ring = mRing;
        final Animation animation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                if (mFinishing) {
                    applyFinishTranslation(interpolatedTime, ring);
                } else {
                    final float minProgressArc = getMinProgressArc(ring);
                    final float startingEndTrim = ring.mStartingEndTrim;
                    final float startingTrim = ring.mStartingStartTrim;
                    final float startingRotation = ring.mStartingRotation;
                    updateRingColor(interpolatedTime, ring);
                    if (interpolatedTime <= START_TRIM_DURATION_OFFSET) {
                        final float scaledTime = (interpolatedTime) / (1.0f - START_TRIM_DURATION_OFFSET);
                        ring.mStartTrim = (startingTrim + ((MAX_PROGRESS_ARC - minProgressArc) * MATERIAL_INTERPOLATOR.getInterpolation(scaledTime)));
                    }
                    if (interpolatedTime > END_TRIM_START_DELAY_OFFSET) {
                        final float minArc = MAX_PROGRESS_ARC - minProgressArc;
                        float scaledTime = (interpolatedTime - START_TRIM_DURATION_OFFSET) / (1.0f - START_TRIM_DURATION_OFFSET);
                        ring.mEndTrim = (startingEndTrim + (minArc * MATERIAL_INTERPOLATOR.getInterpolation(scaledTime)));
                    }
                    setProgressRotation(startingRotation + (0.25f * interpolatedTime));
                    float groupRotation = ((FULL_ROTATION / NUM_POINTS) * interpolatedTime) + (FULL_ROTATION * (mRotationCount / NUM_POINTS));
                    setRotation(groupRotation);
                }
            }
        };
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(LINEAR_INTERPOLATOR);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mRotationCount = 0;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.mStartTrim = (ring.mEndTrim);
                if (mFinishing) {
                    mFinishing = false;
                    animation.setDuration(ANIMATION_DURATION);
                    showArrow(false);
                } else {
                    mRotationCount = (mRotationCount + 1) % (NUM_POINTS);
                }
            }
        });
        mAnimation = animation;
    }

    private class Ring {
        final RectF mTempBounds = new RectF();
        final Paint mPaint = new Paint();
        final Paint mArrowPaint = new Paint();
        float mStartTrim = 0.0f;
        float mEndTrim = 0.0f;
        float mRotation = 0.0f;
        float mStrokeWidth = 5.0f;
        float mStrokeInset = 2.5f;
        int[] mColors;
        int mColorIndex;
        float mStartingStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        boolean mShowArrow;
        Path mArrow;
        float mArrowScale;
        double mRingCenterRadius;
        int mArrowWidth;
        int mArrowHeight;
        int mAlpha;
        final Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int mBackgroundColor;
        int mCurrentColor;

        Ring() {
            mPaint.setStrokeCap(Paint.Cap.SQUARE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mArrowPaint.setStyle(Paint.Style.FILL);
            mArrowPaint.setAntiAlias(true);
        }

        public void draw(Canvas c, Rect bounds) {
            final RectF arcBounds = mTempBounds;
            arcBounds.set(bounds);
            arcBounds.inset(mStrokeInset, mStrokeInset);
            final float startAngle = (mStartTrim + mRotation) * 360;
            final float endAngle = (mEndTrim + mRotation) * 360;
            float sweepAngle = endAngle - startAngle;
            if (sweepAngle != 0) {
                mPaint.setColor(mCurrentColor);
                c.drawArc(arcBounds, startAngle, sweepAngle, false, mPaint);
            }
            drawTriangle(c, startAngle, sweepAngle, bounds);
            if (mAlpha < 255) {
                mCirclePaint.setColor(mBackgroundColor);
                mCirclePaint.setAlpha(255 - mAlpha);
                c.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), bounds.width() / 2, mCirclePaint);
            }
        }

        private void drawTriangle(Canvas c, float startAngle, float sweepAngle, Rect bounds) {
            if (mShowArrow) {
                if (mArrow == null) {
                    mArrow = new Path();
                    mArrow.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    mArrow.reset();
                }
                float inset = (int) mStrokeInset / 2 * mArrowScale;
                float x = (float) (mRingCenterRadius * Math.cos(0) + bounds.exactCenterX());
                float y = (float) (mRingCenterRadius * Math.sin(0) + bounds.exactCenterY());
                mArrow.moveTo(0, 0);
                mArrow.lineTo(mArrowWidth * mArrowScale, 0);
                mArrow.lineTo((mArrowWidth * mArrowScale / 2), (mArrowHeight * mArrowScale));
                mArrow.offset(x - inset, y);
                mArrow.close();
                mArrowPaint.setColor(mCurrentColor);
                c.rotate(startAngle + sweepAngle - ARROW_OFFSET_ANGLE, bounds.exactCenterX(), bounds.exactCenterY());
                c.drawPath(mArrow, mArrowPaint);
            }
        }

        public void setColorIndex(int index) {
            mColorIndex = index;
            mCurrentColor = mColors[mColorIndex];
        }

        public int getNextColor() {
            return mColors[getNextColorIndex()];
        }

        private int getNextColorIndex() {
            return (mColorIndex + 1) % (mColors.length);
        }

        public void goToNextColor() {
            setColorIndex(getNextColorIndex());
        }

        public int getStartingColor() {
            return mColors[mColorIndex];
        }

        public void setInsets(int width, int height) {
            final float minEdge = (float) Math.min(width, height);
            float insets;
            if (mRingCenterRadius <= 0 || minEdge < 0) {
                insets = (float) Math.ceil(mStrokeWidth / 2.0f);
            } else {
                insets = (float) (minEdge / 2.0f - mRingCenterRadius);
            }
            mStrokeInset = insets;
        }

        public void storeOriginals() {
            mStartingStartTrim = mStartTrim;
            mStartingEndTrim = mEndTrim;
            mStartingRotation = mRotation;
        }

        public void resetOriginals() {
            mStartingStartTrim = 0;
            mStartingEndTrim = 0;
            mStartingRotation = 0;
            mStartTrim = (0);
            mEndTrim = (0);
            mRotation = (0);
        }
    }
}
