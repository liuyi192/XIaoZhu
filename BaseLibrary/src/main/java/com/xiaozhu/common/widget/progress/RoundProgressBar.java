package com.xiaozhu.common.widget.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaozhu.common.R;

/**
 * @说明 圆角或者圆形进度条
 * @作者 liuyi
 * @时间 2018/4/18 15:00
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class RoundProgressBar extends View {
    private static final int STYLE_HORIZONTAL = 0;
    private static final int STYLE_ROUND = 1;
    private static final int STYLE_SECTOR = 2;
    //圆形进度条边框宽度
    private int strokeWidth = 20;
    //进度条中心X坐标
    private int centerX;
    //进度条中心Y坐标
    private int centerY;
    //进度提示文字大小
    private int percentTextSize = 18;
    //进度提示文字颜色
    private int percentTextColor = 0xff009ACD;
    //进度条背景颜色
    private int progressBarBgColor = 0xff636363;
    //进度颜色
    private int progressColor = 0xff00C5CD;
    //扇形扫描进度的颜色
    private int sectorColor = 0xaaffffff;
    //扇形扫描背景
    private int unSweepColor = 0xaa5e5e5e;
    //进度条样式（水平/圆形）
    private int orientation = STYLE_HORIZONTAL;
    //圆形进度条半径
    private int radius = 30;
    //进度最大值
    private int max = 100;
    //进度值
    private int progress = 0;
    //水平进度条是否是空心
    private boolean isHorizonStroke;
    //水平进度圆角值
    private int rectRound = 5;
    //进度文字是否显示百分号
    private boolean showPercentSign;
    private Paint mPaint;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        percentTextColor = array.getColor(R.styleable.RoundProgressBar_percent_text_color, percentTextColor);
        progressBarBgColor = array.getColor(R.styleable.RoundProgressBar_progressBarBgColor, progressBarBgColor);
        progressColor = array.getColor(R.styleable.RoundProgressBar_progressColor, progressColor);
        sectorColor = array.getColor(R.styleable.RoundProgressBar_sectorColor, sectorColor);
        unSweepColor = array.getColor(R.styleable.RoundProgressBar_unSweepColor, unSweepColor);
        percentTextSize = (int) array.getDimension(R.styleable.RoundProgressBar_percent_text_size, percentTextSize);
        strokeWidth = (int) array.getDimension(R.styleable.RoundProgressBar_percent_stroke_width, strokeWidth);
        rectRound = (int) array.getDimension(R.styleable.RoundProgressBar_rect_round, rectRound);
        orientation = array.getInteger(R.styleable.RoundProgressBar_orientation, STYLE_HORIZONTAL);
        isHorizonStroke = array.getBoolean(R.styleable.RoundProgressBar_isHorizonStroke, false);
        showPercentSign = array.getBoolean(R.styleable.RoundProgressBar_showPercentSign, true);
        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = centerX - strokeWidth / 2;
        if (orientation == STYLE_HORIZONTAL) {
            drawHorizontalRectProgressBar(canvas, mPaint);
        } else if (orientation == STYLE_ROUND) {
            drawRoundProgressBar(canvas, mPaint);
        } else if (orientation == STYLE_SECTOR) {
            drawSectorProgressBar(canvas, mPaint);
        } else {
            drawHorizontalRectProgressBar(canvas, mPaint);
        }
    }

    /**
     * 绘制圆形进度条
     *
     * @param canvas
     * @param paint
     */
    private void drawRoundProgressBar(Canvas canvas, Paint paint) {
        // 初始化画笔属性
        paint.setColor(progressBarBgColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // 画圆
        canvas.drawCircle(centerX, centerY, radius, paint);
        // 画圆形进度
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        RectF oval = new RectF(centerX - radius, centerY - radius, radius + centerX, radius + centerY);
        canvas.drawArc(oval, -90, 360 * progress / max, false, paint);
        // 画进度文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(percentTextColor);
        paint.setTextSize(percentTextSize);
        String percent = (int) (progress * 100 / max) + "%";
        Rect rect = new Rect();
        paint.getTextBounds(percent, 0, percent.length(), rect);
        float textWidth = rect.width();
        float textHeight = rect.height();
        if (textWidth >= radius * 2) {
            textWidth = radius * 2;
        }
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float baseline = (getMeasuredHeight() - metrics.bottom + metrics.top) / 2 - metrics.top;
        canvas.drawText(percent, centerX - textWidth / 2, baseline, paint);
    }

    /**
     * 绘制水平矩形进度条
     *
     * @param canvas
     */
    private void drawHorizontalRectProgressBar(Canvas canvas, Paint paint) {
        // 初始化画笔属性
        paint.setColor(progressBarBgColor);
        if (isHorizonStroke) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
        // 画水平矩形
        canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2, centerX + getWidth() / 2, centerY + getHeight() / 2), rectRound, rectRound, paint);
        // 画水平进度
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(progressColor);
        if (isHorizonStroke) {
            canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2, ((progress * 100 / max) * getWidth()) / 100, centerY + getHeight() / 2), rectRound, rectRound, paint);
        } else {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2, ((progress * 100 / max) * getWidth()) / 100, centerY + getHeight() / 2), rectRound, rectRound, paint);
            paint.setXfermode(null);
        }
        // 画进度文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(percentTextColor);
        paint.setTextSize(percentTextSize);
        String percent = (int) (progress * 100 / max) + "%";
        Rect rect = new Rect();
        paint.getTextBounds(percent, 0, percent.length(), rect);
        float textWidth = rect.width();
        float textHeight = rect.height();
        if (textWidth >= getWidth()) {
            textWidth = getWidth();
        }
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float baseline = (getMeasuredHeight() - metrics.bottom + metrics.top) / 2 - metrics.top;
        canvas.drawText(percent, centerX - textWidth / 2, baseline, paint);

    }

    /**
     * 绘制扇形扫描式进度
     *
     * @param canvas
     * @param paint
     */
    private void drawSectorProgressBar(Canvas canvas, Paint paint) {
        // 初始化画笔属性
        paint.setColor(sectorColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        // 绘外圈
        canvas.drawCircle(centerX, centerY, radius, paint);
        // 绘内圈
        paint.setColor(unSweepColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius - 2, paint);

        paint.setColor(sectorColor);
        RectF oval = new RectF(centerX - radius + 2, centerY - radius + 2, radius + centerX - 2, radius + centerY - 2);
        canvas.drawArc(oval, -90, 360 * progress / max, true, paint);
    }

    public void setProgress(int progress) {
        if (progress > max) {
            progress = max;
        } else {
            this.progress = progress;
            postInvalidate();
        }
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getPercentTextSize() {
        return percentTextSize;
    }

    public void setPercentTextSize(int percentTextSize) {
        this.percentTextSize = percentTextSize;
    }

    public int getPercentTextColor() {
        return percentTextColor;
    }

    public void setPercentTextColor(int percentTextColor) {
        this.percentTextColor = percentTextColor;
    }

    public int getProgressBarBgColor() {
        return progressBarBgColor;
    }

    public void setProgressBarBgColor(int progressBarBgColor) {
        this.progressBarBgColor = progressBarBgColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public boolean isHorizonStroke() {
        return isHorizonStroke;
    }

    public void setHorizonStroke(boolean isHorizonStroke) {
        this.isHorizonStroke = isHorizonStroke;
    }

    public int getRectRound() {
        return rectRound;
    }

    public void setRectRound(int rectRound) {
        this.rectRound = rectRound;
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return progress;
    }

}
